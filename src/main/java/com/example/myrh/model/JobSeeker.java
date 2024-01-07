package com.example.myrh.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "JobSeeker")
public class JobSeeker extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    

    // The entity that specifies the @JoinTable is the owning side of the relationship and
    // the entity that specifies the mappedBy attribute is the inverse side.
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "jobSeekers"
    )
   Set<Offer> offers = new HashSet<>();

}
