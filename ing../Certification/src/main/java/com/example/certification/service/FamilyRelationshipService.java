package com.example.certification.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.FamilyRelationModifyDTO;
import org.example.domain.FamilyRelationshipDTO;
import org.example.entity.FamilyRelationship;
import org.example.entity.Resident;
import org.example.exception.NotFoundResidentException;
import org.example.repository.FamilyRelationshipRepository;
import org.example.repository.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FamilyRelationshipService {

    private final FamilyRelationshipRepository familyRelationshipRepository;
    private final ResidentRepository residentRepository;

    public FamilyRelationshipDTO registerFamilyRelationship(Long serialNumber, FamilyRelationshipDTO familyRelationshipDTO) {

        Resident resident = residentRepository.findById(serialNumber).orElseThrow(NotFoundResidentException::new);

        FamilyRelationship familyRelationship = new FamilyRelationship().builder()
                .pk(new FamilyRelationship.Pk(serialNumber,familyRelationshipDTO.getFamilySerialNumber()))
                .resident(resident)
                .familyRelationshipCode(familyRelationshipDTO.getFamilyRelationshipCode())
                .build();
        familyRelationshipRepository.saveAndFlush(familyRelationship);
        return familyRelationshipDTO;
    }

    public FamilyRelationModifyDTO modifyFamilyRelationship(FamilyRelationModifyDTO familyRelationshipDTO, Long familyNumber, Long number) {

        FamilyRelationship relationship = familyRelationshipRepository.findByPk_FamilyResidentSerialNumberAndPk_BaseResidentSerialNumber(familyNumber, number);

        relationship.modifyRelationInfo(
                familyRelationshipDTO.getFamilyRelationshipCode()
        );
        familyRelationshipRepository.saveAndFlush(relationship);

        return familyRelationshipDTO;
    }
}
