package com.example.certification.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.example.certification.entity.Resident;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FamilyRelationshipReportDTO {

    String familyRelationshipCode;
    Resident familyResident;
}
