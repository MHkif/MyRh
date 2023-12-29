package com.example.myrh.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.MapsId;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class JobApplicantId implements Serializable {
    @MapsId("offer_id")
    private Integer offer_id;

    @MapsId("jobSeeker_id")
    private Integer jobSeeker_id;
}
