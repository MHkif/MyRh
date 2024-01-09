package com.example.myrh.repository;

import com.example.myrh.dto.responses.OfferRes;
import com.example.myrh.enums.OfferStatus;
import com.example.myrh.model.Company;
import com.example.myrh.model.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;
import java.util.Optional;

public interface OfferRepo extends JpaRepository<Offer, Integer>, JpaSpecificationExecutor<Offer> {

    Page<Offer> findAllByStatus( Pageable pageable, OfferStatus status, Specification<Offer> spec );

    Collection<Offer> findAllByCompany(Company company);
}
