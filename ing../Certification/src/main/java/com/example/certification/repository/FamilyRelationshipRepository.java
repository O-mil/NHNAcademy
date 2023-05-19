package com.example.certification.repository;

import org.example.domain.FamilyRelationshipDTO;
import org.example.entity.FamilyRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship,FamilyRelationship.Pk> {
    List<FamilyRelationshipDTO> findByPkFamilyResidentSerialNumber(Long serialNumber);

    @Query("SELECT O FROM FamilyRelationship O where O.pk.familyResidentSerialNumber=?1 and O.pk.baseResidentSerialNumber=?2")
    FamilyRelationship findByPk_FamilyResidentSerialNumberAndPk_BaseResidentSerialNumber(Long familyResidentSerialNumber, Long baseSerialNumber);
}
