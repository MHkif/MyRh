package com.example.myrh.service.impl;

import com.example.myrh.dto.requests.CompanyReq;
import com.example.myrh.dto.responses.CompanyRes;
import com.example.myrh.dto.responses.RecruiterRes;
import com.example.myrh.exception.BadRequestException;
import com.example.myrh.mapper.CompanyMapper;
import com.example.myrh.model.Company;
import com.example.myrh.model.Confirmation;
import com.example.myrh.model.Recruiter;
import com.example.myrh.repository.CompanyRepo;
import com.example.myrh.repository.ConfirmationRepo;
import com.example.myrh.service.ICompanyService;
import com.example.myrh.exception.NotFoundException;
import com.example.myrh.service.IEmailService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements ICompanyService {

    private final CompanyRepo repository;
    private final ConfirmationRepo confirmationRepo;
    private final CompanyMapper mapper;
    private final IEmailService emailService;
    @Value("${spring.mail.properties.verify.host}")
    private String host;

    @Autowired
    public CompanyServiceImpl(CompanyRepo repository, CompanyMapper mapper, ConfirmationRepo confirmationRepo, IEmailService emailService) {
        this.repository = repository;
        this.mapper = mapper;
        this.emailService = emailService;
        this.confirmationRepo = confirmationRepo;
    }


    @Override
    public CompanyRes getById(Integer id) {
        Optional<Company> company = repository.findById(id);
        return company.map(mapper::toRes).orElseThrow(() -> new EntityNotFoundException("Comapny Not Exist with the given Id : " + id)
        );
    }

    @Override
    public Page<CompanyRes> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repository.findAll(pageRequest).map(mapper::toRes);
    }

    @Override
    public CompanyRes create(CompanyReq request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException("Email Already Taken");
        }

        request.setEnabled(false);
        Company company = repository.save(mapper.reqToEntity(request));
        CompanyRes res = mapper.toRes(company);

        Confirmation confirmation = new Confirmation(company);
        confirmationRepo.save(confirmation);

        String name = res.getName();
        String sendingTo = res.getEmail();
        String url = "/myrh/api/v1/companies/confirm-account?token=";
        String subject = "MyRH : Email Verification ";
        String body = verificationEmailMessage(name, url, this.host,confirmation.getToken());
        emailService.sendSimpleMailMessage(name, sendingTo, subject, body);
        return res;

    }

    @Override
    public CompanyRes auth(String email, String password) {
        if (repository.existsByEmail(email)) {
            Company company = repository.findByEmail(email).get();
            if (!Objects.equals(company.getPassword(), password)) {
                throw new IllegalStateException("Incorrect Passowrd");
            }
            return  mapper.toRes(company);

        } else {
            throw new EntityNotFoundException("No Company Found with this Email");
        }
    }

    @Override
    public CompanyRes update(int id, CompanyRes res) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public Boolean verifyToken(String token) throws Exception {
        Confirmation confirmation = confirmationRepo.findByToken(token);

        boolean isLessThan3Minutes = this.validateDate(confirmation.getCreatedDate());

         if (!isLessThan3Minutes && !confirmation.isVerified()) {
           // throw new IllegalStateException("Expired Token : " + token);
            throw new BadRequestException(("Expired Token : " + token));
        }else if(confirmation.isVerified()){
            throw new Exception("Your Account is Already verified .");
        }

        Optional<Company> company = repository.findByEmail(confirmation.getCompany().getEmail());
        if (company.isEmpty()) {
            return Boolean.FALSE;
        }

        company.get().setEnabled(true);
        repository.save(company.get());

       confirmation.setVerified(true);
       confirmation.setVerifiedAt(LocalDateTime.now());
       confirmationRepo.save(confirmation);
        return Boolean.TRUE;
    }

    public boolean validateDate(LocalDateTime date) {
        return LocalDateTime.now().getMinute() - date.getMinute() <= 3;

    }

    private String verificationEmailMessage(String name, String url, String host, String token) {
        return "Hello " + name + ", \n\n" +
                "You account has been created successfully," +
                " Please click the link below to verify your account . \n\n" +
                host + url + token + " \n\n" +
                "Note : Link will be expired after 3 minutes. \n\n" +
                "The Support Team MyRH .";


    }


}
