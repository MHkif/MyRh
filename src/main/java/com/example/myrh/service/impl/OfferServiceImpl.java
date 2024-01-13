package com.example.myrh.service.impl;

import com.example.myrh.dto.requests.OfferReq;
import com.example.myrh.dto.responses.CandidateOffersApply;
import com.example.myrh.dto.responses.JobSeekerOfferInsightsResponse;
import com.example.myrh.dto.responses.OfferRes;
import com.example.myrh.enums.JobApplicationStatus;
import com.example.myrh.enums.OfferStatus;
import com.example.myrh.enums.StudyLevel;
import com.example.myrh.enums.SubscriptionStatus;
import com.example.myrh.exception.BadRequestException;
import com.example.myrh.exception.NotFoundException;
import com.example.myrh.mapper.OfferMapper;
import com.example.myrh.model.Company;
import com.example.myrh.model.JobSeeker;
import com.example.myrh.model.Offer;
import com.example.myrh.repository.CompanyRepo;
import com.example.myrh.repository.JobApplicantRepo;
import com.example.myrh.repository.JobSeekerRepo;
import com.example.myrh.repository.OfferRepo;
import com.example.myrh.service.IOfferService;
import com.example.myrh.service.IOfferInsightsService;
import com.example.myrh.specifications.OfferSpecifications;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OfferServiceImpl implements IOfferService, IOfferInsightsService {
    private final OfferRepo repository;
    private final JobSeekerRepo jobSeekerRepository;
    private final JobApplicantRepo jobApplicantRepo;
    private final CompanyRepo companyRepo;
    private final OfferMapper mapper;


    @Autowired
    public OfferServiceImpl(OfferRepo repository, JobSeekerRepo jobSeekerRepository, JobApplicantRepo jobApplicantRepo, CompanyRepo companyRepo, OfferMapper mapper) {
        this.repository = repository;
        this.jobSeekerRepository = jobSeekerRepository;
        this.jobApplicantRepo = jobApplicantRepo;
        this.companyRepo = companyRepo;
        this.mapper = mapper;
    }

    @Override
    public Page<OfferRes> search(int page, int size, String title, String description, String domain, String city, StudyLevel level, String job) {
        Specification<Offer> spec = buildSpecification(title, description, domain, city, level, job);

        if (size > 10) {
            size = 10;
        }

        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return repository.findAll(spec, pageRequest).map(mapper::toRes);
    }

    @Override
    public OfferRes updateVisibility(int offerId, String visible) {
        try {

            Offer offer = repository.findById(offerId).orElseThrow(
                    () -> new EntityNotFoundException("Offer Not Found"));
            offer.setStatus(OfferStatus.valueOf(visible));

            return mapper.toRes(repository.save(offer));

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid Visibility");
        }
    }

    private Specification<Offer> buildSpecification(String title, String description, String domain, String city, StudyLevel level, String job) {
        Specification<Offer> spec = Specification.where(null);


        if (title != null && !title.isEmpty()) {
            spec = spec.and(OfferSpecifications.hasTitle(title));
        }

        if (description != null) {
            spec = spec.and(OfferSpecifications.hasDescription(description));
        }

        if (domain != null) {
            spec = spec.and(OfferSpecifications.hasDomain(domain));
        }

        if (city != null) {
            spec = spec.and(OfferSpecifications.hasCity(city));
        }

        if (level != null) {
            spec = spec.and(OfferSpecifications.hasLevel(level));
        }

        if (job != null) {
            spec = spec.and(OfferSpecifications.hasJob(job));
        }

        return spec;

    }

    @Override
    public OfferRes getById(Integer id) {
        Optional<Offer> offer = repository.findById(id);
        return offer.map(mapper::toRes).orElseThrow(() -> new EntityNotFoundException("Offer Not Found with the given id"));

    }

    @Override
    public Page<OfferRes> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repository.findAll(pageRequest).map(mapper::toRes);
    }


    @Override
    public OfferRes create(OfferReq request) {
        if (!companyRepo.existsById(request.getCompany().getId())) {
            throw new EntityNotFoundException("Company Not Found");
        }

        if(!verifyCompanySubscription(request.getCompany().getId())){
            throw new BadRequestException("You have reached the limited offers on your pack "+request.getCompany().getSubscription()+" , upgrade your subscription");
        }

        Offer offer = repository.save(mapper.reqToEntity(request));
        return mapper.toRes(offer);
    }


    @Transactional
    @Override
    public OfferRes update(Integer id, OfferRes res) {

        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Offer Not Found");
        }

        Offer offer = repository.save(mapper.resToEntity(res));
        return mapper.toRes(offer);


    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public JobSeekerOfferInsightsResponse getCandidatesOfferInsights(int seekerId) {
        // : 08-01-2024 avoir des statistiques des offres d'emploi par candidats id
        JobSeeker jobSeeker = this.jobSeekerRepository.findById(seekerId).orElseThrow(() -> new NotFoundException("Candidate with "+seekerId+" not found"));
        JobSeekerOfferInsightsResponse response = new JobSeekerOfferInsightsResponse();
        response.setCandidateId(jobSeeker.getId());

        //: 08-01-2024 get all offers applied by this candidate then map the to the (!CandidateOffersApplyCollection)
        //ALL OFFERS APPLIED BY THIS CANDIDATE
        AtomicInteger nbCandidatesAccepted = new AtomicInteger();
        AtomicInteger nbCandidatesRefused = new AtomicInteger();
        AtomicInteger nbCandidatesInProcess = new AtomicInteger();

        this.jobApplicantRepo.getAllById_JobSeeker_id(seekerId).forEach(jobApplicant -> {

            if (jobApplicant.getStatus()== JobApplicationStatus.ACCEPTED) {
                nbCandidatesAccepted.set(nbCandidatesAccepted.get() + 1);
            }
            else if (jobApplicant.getStatus()== JobApplicationStatus.REFUSED) {
                nbCandidatesRefused.set(nbCandidatesRefused.get() + 1);
            }
            else if (jobApplicant.getStatus()== JobApplicationStatus.IN_PROCESS) {
                nbCandidatesInProcess.set(nbCandidatesInProcess.get() + 1);
            }
            Integer id = jobApplicant.getId().getOffer_id();
            String title = this.repository.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("Offer Not Found")).getTitle();

            response.getCandidateOffersApplyCollection().add(new CandidateOffersApply(id, title));


        });

        response.setNbCandidatesAccepted(nbCandidatesAccepted.get());
        response.setNbCandidatesRefused(nbCandidatesRefused.get());
        response.setNbCandidatesInProcess(nbCandidatesInProcess.get());
        response.setJobSeeker_status(jobSeeker.getStatus());
        response.setJobSeekerName(jobSeeker.getFirst_name()+" "+jobSeeker.getLast_name());
        return response;
    }

    @Override
    public Page<JobSeekerOfferInsightsResponse> getCandidatesOfferInsights(int page, int size) {
        // TODO : 08-01-2024 avoir des statistiques des offres d'emploi par candidats id all
        return null;
    }

    @Override
    public Collection<JobSeekerOfferInsightsResponse> getAllCandidatesOfferInsights(String id , Map<String, String> params) {
        //get all jobSeeker which used to apply to this company than call the function before .
        Company company =  this.companyRepo.findById(Integer.parseInt(id)).orElseThrow(() -> new NotFoundException("Company with "+id+" not found"));
        Collection<JobSeekerOfferInsightsResponse> jobSeekerOfferInsightsResponseCollection = new ArrayList<>();
//        PageRequest pageRequest = PageRequest.of(Integer.parseInt(params.get("page")), Integer.parseInt(params.get("size")));
        //find offers byt company .... then loop into each offer and get all job application from them
        this.repository.findAllByCompany(company).forEach(offer -> {
            this.jobApplicantRepo.getAllById_Offer_id(offer.getId()).forEach(jobApplicant -> {
                jobSeekerOfferInsightsResponseCollection.add(this.getCandidatesOfferInsights(jobApplicant.getId().getJobSeeker_id()));

            });
        });
        return jobSeekerOfferInsightsResponseCollection;
    }

    @Override
    public boolean verifyCompanySubscription(int companyId){
        Company company = companyRepo.findById(companyId).orElseThrow(() -> new EntityNotFoundException("Company not found with the given id"));
        Collection<Offer> offerList = repository.findAllByCompany(company);

        if(company.getSubscription() == SubscriptionStatus.FREEMIUM){
            return offerList.size() < 3;
        }else if(company.getSubscription() == SubscriptionStatus.BASIC){
            return offerList.size() < 10;
        }
        return company.getSubscription() == SubscriptionStatus.PREMIUM;
    }
}