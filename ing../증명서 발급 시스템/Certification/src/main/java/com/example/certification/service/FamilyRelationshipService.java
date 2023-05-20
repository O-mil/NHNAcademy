package com.example.certification.service;

import lombok.RequiredArgsConstructor;
import com.example.certification.domain.FamilyRelationModifyDTO;
import com.example.certification.domain.FamilyRelationshipDTO;
import com.example.certification.entity.FamilyRelationship;
import com.example.certification.entity.Resident;
import com.example.certification.exception.NotFoundResidentException;
import com.example.certification.repository.FamilyRelationshipRepository;
import com.example.certification.repository.ResidentRepository;
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
