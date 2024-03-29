package com.example.certification.repository;

import com.example.certification.entity.BirthDeathReportResident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirthDeathReportResidentRepository extends JpaRepository<BirthDeathReportResident, BirthDeathReportResident.Pk> {
    BirthDeathReportResident findByPk_ResidentSerialNumberAndPk_BirthDeathTypeCode(Long residentSerialNumber, String birthDeathTypeCode);
}
