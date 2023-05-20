package com.example.certification.repository;

import com.example.certification.entity.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HouseholdRepository extends JpaRepository<Household, Long> {

    @Query("SELECT H FROM Household H WHERE H.householdResidentSerialNumber.residentSerialNumber = ?1")
    Household findByResidentSerialNumber (Long residentSerialNumber);
}
