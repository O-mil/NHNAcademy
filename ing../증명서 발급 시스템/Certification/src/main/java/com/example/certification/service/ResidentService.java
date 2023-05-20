package com.example.certification.service;


import lombok.RequiredArgsConstructor;
import com.example.certification.domain.ResidentModifyDTO;
import com.example.certification.domain.ResidentRegisterDTO;
import com.example.certification.entity.Resident;
import com.example.certification.exception.NotFoundResidentException;
import com.example.certification.repository.ResidentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ResidentService {

    private final ResidentRepository residentRepository;

    public Resident register(ResidentRegisterDTO residentRegisterDTO){

        Resident resident = new Resident().builder()
                .name(residentRegisterDTO.getName())
                .residentRegistrationNumber(residentRegisterDTO.getResidentRegistrationNumber())
                .birthDate(residentRegisterDTO.getBirthDate())
                .birthPlaceCode(residentRegisterDTO.getBirthPlaceCode())
                .registrationBaseAddress(residentRegisterDTO.getRegistrationBaseAddress())
                .deathPlaceCode(residentRegisterDTO.getDeathPlaceCode())
                .deathDate(residentRegisterDTO.getDeathDate())
                .genderCode(residentRegisterDTO.getGenderCode())
                .deathPlaceAddress(residentRegisterDTO.getDeathPlaceAddress())
                .build();

        return residentRepository.saveAndFlush(resident);
    }

    public Resident modify(Long serialNum, ResidentModifyDTO residentModifyDTO) {

        Resident resident = residentRepository.findById(serialNum).orElseThrow(NotFoundResidentException::new);

        resident.modifyResidentInfo(
                residentModifyDTO.getName(),
                residentModifyDTO.getRegistrationBaseAddress(),
                residentModifyDTO.getDeathDate(),
                residentModifyDTO.getDeathPlaceCode(),
                residentModifyDTO.getDeathPlaceAddress()
        );

        return residentRepository.saveAndFlush(resident);
    }

    public Page<Resident> findAll(Pageable pageable) {
        return residentRepository.findAll(pageable);
    }

    public Resident findBySerialId(Long serialNumber) {
        return residentRepository.findById(serialNumber).orElseThrow(NotFoundResidentException::new);
    }
}
