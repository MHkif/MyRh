package com.example.myrh.service.impl;

import com.example.myrh.dto.requests.RecruiterReq;
import com.example.myrh.dto.responses.CompanyRes;
import com.example.myrh.dto.responses.RecruiterRes;
import com.example.myrh.mapper.CompanyMapper;
import com.example.myrh.mapper.RecruiterMapper;
import com.example.myrh.model.Confirmation;
import com.example.myrh.model.Recruiter;
import com.example.myrh.repository.CompanyRepo;
import com.example.myrh.repository.ConfirmationRepo;
import com.example.myrh.repository.RecruiterRepo;
import com.example.myrh.service.IEmailService;
import com.example.myrh.service.IRecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RecruiterServiceImpl implements IRecruiterService {

    private final RecruiterRepo repository;
    private final CompanyRepo companyRepo;
    private final ConfirmationRepo confirmationRepo;
    private final RecruiterMapper mapper;
    private final CompanyMapper companyMapper;
    private final IEmailService emailService;
    @Value("${spring.mail.properties.verify.host}")
    private String host;

    @Autowired
    public RecruiterServiceImpl(RecruiterRepo repository, CompanyRepo companyRepo, ConfirmationRepo confirmationRepo, RecruiterMapper mapper, CompanyMapper companyMapper, IEmailService emailService) {
        this.repository = repository;
        this.companyRepo = companyRepo;
        this.confirmationRepo = confirmationRepo;
        this.mapper = mapper;
        this.companyMapper = companyMapper;
        this.emailService = emailService;
    }

    @Override
    public RecruiterRes getById(Integer id) {
        Recruiter recruiter = repository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Recruiter not found"));
        return mapper.toRes(recruiter);
    }

    @Override
    public Page<RecruiterRes> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repository.findAll(pageRequest).map(mapper::toRes);
    }

    @Override
    public RecruiterRes create(RecruiterReq request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException("Email Already Taken");
        }
        if (!companyRepo.existsById(request.getCompany().getId())) {
            throw new IllegalStateException("Company not found");
        }
        request.setEnabled(false);

        CompanyRes companyRes = companyMapper.toRes(companyRepo.findById(request.getCompany().getId()).get());
        Recruiter recruiter = repository.save(mapper.reqToEntity(request));

        Confirmation confirmation = new Confirmation(recruiter);
        confirmationRepo.save(confirmation);

        RecruiterRes res = mapper.toRes(recruiter);
        res.setCompany(companyRes);
        String name = res.getFirst_name() + " " + res.getLast_name();
        String sendingTo = res.getEmail();
        String url = "/myrh/api/v1/recruiters/confirm-account?token=";

        String subject = "MyRH : Email Verification ";
        String body = verificationEmailMessage(name, url, this.host,confirmation.getToken());
        emailService.sendSimpleMailMessage(name, sendingTo, subject, body);
        return res;
    }

    @Override
    public RecruiterRes update(int id, RecruiterRes res) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public Boolean verifyToken(String token) {
        Confirmation confirmation = confirmationRepo.findByToken(token);

        boolean isLessThan3Minutes = this.validateDate(confirmation.getCreatedDate());
        if (!isLessThan3Minutes) {
            throw new IllegalStateException("Expired Token : " + token);
        }
        Recruiter recruiter = repository.findByEmail(confirmation.getRecruiter().getEmail());
        if (recruiter == null) {
            return Boolean.FALSE;
        }

        recruiter.setEnabled(true);
        repository.save(recruiter);
        confirmationRepo.delete(confirmation);
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
