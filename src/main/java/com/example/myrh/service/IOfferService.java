package com.example.myrh.service;

import com.example.myrh.dto.requests.OfferReq;
import com.example.myrh.dto.responses.OfferRes;
import com.example.myrh.enums.OfferStatus;
import com.example.myrh.enums.StudyLevel;
import com.example.myrh.model.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOfferService extends IService<Offer, Integer, OfferReq, OfferRes>{
    Page<OfferRes> search(int page, int size, String title, String description, String domain, String city, StudyLevel level, String job);
    OfferRes updateVisibility(int offerId, String offerStatus);


    boolean verifyCompanySubscription(int companyId);
}
