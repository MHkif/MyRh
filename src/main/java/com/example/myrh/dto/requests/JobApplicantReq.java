package com.example.myrh.dto.requests;

import com.example.myrh.model.JobApplicantId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MapsId;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
public class JobApplicantReq {
    private JobApplicantId id;
    private LocalDateTime createdDate = LocalDateTime.now();
    private Boolean isViewed = false;
}
