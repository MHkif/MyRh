package com.example.myrh.repository;

import com.example.myrh.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OfferRepo extends JpaRepository<Offer, Integer>, JpaSpecificationExecutor<Offer> {

}
