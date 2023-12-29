package com.example.myrh.repository;

import com.example.myrh.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepo extends JpaRepository<City, Integer> {
}
