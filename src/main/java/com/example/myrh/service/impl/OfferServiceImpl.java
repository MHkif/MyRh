package com.example.myrh.service.impl;

import com.example.myrh.dto.requests.OfferReq;
import com.example.myrh.dto.responses.OfferRes;
import com.example.myrh.mapper.OfferMapper;
import com.example.myrh.model.Offer;
import com.example.myrh.repository.OfferRepo;
import com.example.myrh.repository.RecruiterRepo;
import com.example.myrh.service.IOfferService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OfferServiceImpl implements IOfferService {
    private final OfferRepo repository;
    private final RecruiterRepo recruiterRepo;
    private final OfferMapper mapper;

    @Autowired
    public OfferServiceImpl(OfferRepo repository, RecruiterRepo recruiterRepo, OfferMapper mapper) {
        this.repository = repository;
        this.recruiterRepo = recruiterRepo;
        this.mapper = mapper;
    }

    @Override
    public OfferRes getById(Integer id) {
        Optional<Offer> offer = repository.findById(id);
        return offer.map(mapper::toRes).orElseThrow(() ->  new IllegalStateException("Offer Not Found"));

    }

    @Override
    public Page<OfferRes> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repository.findAll(pageRequest).map(mapper::toRes);
    }

    @Override
    public OfferRes create(OfferReq request) {
        if(!recruiterRepo.existsById(request.getRecruiter().getId())){
            throw new IllegalStateException("Recruiter Not Found");
        }

        Offer offer = repository.save(mapper.reqToEntity(request));
        return mapper.toRes(offer);
    }


    @Transactional
    @Override
    public OfferRes update(int id, OfferRes res) {

        if(!repository.existsById(id)){
            throw new IllegalStateException("Offer Not Found");
        }

        Offer offer = repository.save(mapper.resToEntity(res));
        return mapper.toRes(offer);


    }

    @Override
    public void deleteById(int id) {

    }
}
