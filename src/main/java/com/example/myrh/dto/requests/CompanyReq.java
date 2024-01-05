package com.example.myrh.dto.requests;

import com.example.myrh.model.Agent;
import com.example.myrh.model.Recruiter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CompanyReq {

    @Valid
    //private int id;
    @NotNull(message = "name field is required")
    @NotBlank(message = "name field is required")
    @Size(message = "Name must not be more than 50 chars", min = 5, max = 54)
    private String name;
    private String email;
    private String password;
    private String image;
    private boolean enabled;
    /*
    Set<Recruiter> recruiters = new HashSet<>();
    Set<Agent> agents = new HashSet<>();

     */
}
