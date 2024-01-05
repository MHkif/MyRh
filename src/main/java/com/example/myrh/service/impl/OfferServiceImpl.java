package com.example.myrh.service.impl;

import com.example.myrh.dto.requests.OfferReq;
import com.example.myrh.dto.responses.OfferRes;
import com.example.myrh.enums.StudyLevel;
import com.example.myrh.mapper.OfferMapper;
import com.example.myrh.model.Offer;
import com.example.myrh.repository.CompanyRepo;
import com.example.myrh.repository.OfferRepo;
import com.example.myrh.service.IOfferService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OfferServiceImpl implements IOfferService {
    private final OfferRepo repository;
    private final CompanyRepo companyRepo;
    private final OfferMapper mapper;

    @Autowired
    public OfferServiceImpl(OfferRepo repository, CompanyRepo companyRepo, OfferMapper mapper) {
        this.repository = repository;
        this.companyRepo = companyRepo;
        this.mapper = mapper;
    }

    @Override
    public Page<OfferRes> search(int page, int size, String title, String description, String domain, String city, StudyLevel level, String job) {

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


        PageRequest pageRequest = PageRequest.of(page -1, size);
        return repository.findAll(spec, pageRequest).map(mapper::toRes);
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

        Offer offer = repository.save(mapper.reqToEntity(request));
        return mapper.toRes(offer);
    }


    @Transactional
    @Override
    public OfferRes update(int id, OfferRes res) {

        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Offer Not Found");
        }

        Offer offer = repository.save(mapper.resToEntity(res));
        return mapper.toRes(offer);


    }

    @Override
    public void deleteById(int id) {

    }
}
