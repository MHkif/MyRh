package com.example.myrh.model;

import com.example.myrh.enums.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Company{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    private String image;
    private boolean enabled;
    @OneToMany(mappedBy = "company")
    Set<Agent> agents = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private SubscriptionStatus subscription = SubscriptionStatus.FREEMIUM;


    @Override
    public String toString() {
        return super.toString();
    }
}
