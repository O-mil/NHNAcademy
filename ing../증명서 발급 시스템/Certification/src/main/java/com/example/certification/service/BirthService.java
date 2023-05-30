package com.example.certification.service;


import com.example.certification.domain.BirthModifyDTO;
import com.example.certification.domain.BirthReportDTO;
import com.example.certification.entity.BirthDeathReportResident;
import com.example.certification.entity.Resident;
import com.example.certification.exception.NotFoundResidentException;
import com.example.certification.repository.BirthDeathReportResidentRepository;
import com.example.certification.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

@RequiredArgsConstructor
@Transactional
@Service
public class BirthService {

    private final ResidentRepository residentRepository;

    private final BirthDeathReportResidentRepository birthReportRepository;

    private static final String BIRTH_TYPE = "출생";

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public BirthReportDTO registerBirth(Long serialNumber, BirthReportDTO birthReportDTO){

        Resident reportResident = residentRepository.findById(serialNumber).orElseThrow(NotFoundResidentException::new);
        Resident targetResident = residentRepository.findById(birthReportDTO.getResidentSerialNumber()).orElseThrow(NotFoundResidentException::new);

        BirthDeathReportResident birthResident = new BirthDeathReportResident().builder()
                .pk(new BirthDeathReportResident.Pk(birthReportDTO.getResidentSerialNumber(),birthReportDTO.getBirthDeathTypeCode()))
                .resident(targetResident)
                .reportResidentSerialNumber(reportResident)
                .birthDeathReportDate(birthReportDTO.getBirthDeathReportDate().format(formatter))
                .birthReportQualificationsCode(birthReportDTO.getBirthReportQualificationsCode())
                .emailAddress(birthReportDTO.getEmailAddress())
                .phoneNumber(birthReportDTO.getPhoneNumber())
                .build();

        birthReportRepository.saveAndFlush(birthResident);
        return birthReportDTO;
    }

    public BirthModifyDTO modifyBirth(Long targetSerialNumber, BirthModifyDTO birthModifyDTO) {

        BirthDeathReportResident birthReport = birthReportRepository.findByPk_ResidentSerialNumberAndPk_BirthDeathTypeCode(targetSerialNumber, BIRTH_TYPE);

        if(Objects.isNull(birthReport)){
            throw new NotFoundResidentException();
        }

        birthReport.updateReportInfo(birthModifyDTO.getEmailAddress(), birthModifyDTO.getPhoneNumber());
        birthReportRepository.saveAndFlush(birthReport);
        return birthModifyDTO;
    }

    public void deleteBirth(Long targetSerialNumber) {
        BirthDeathReportResident birthReport = birthReportRepository.findByPk_ResidentSerialNumberAndPk_BirthDeathTypeCode(targetSerialNumber, BIRTH_TYPE);

        if(Objects.isNull(birthReport)) {
            throw new NotFoundResidentException();
        }

        birthReportRepository.delete(birthReport);
    }
}
