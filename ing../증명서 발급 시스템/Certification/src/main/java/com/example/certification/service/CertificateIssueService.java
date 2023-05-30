package com.example.certification.service;


import lombok.RequiredArgsConstructor;
import com.example.certification.domain.CertificateIssueDTO;
import com.example.certification.repository.CertificateIssueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CertificateIssueService {

    private final CertificateIssueRepository certificateIssueRepository;

    public CertificateIssueDTO getCertificateInfoByResidentSerialNumber(Long certificateConfirmationNumber, String typeCode) {
        return certificateIssueRepository.getCertificateInfoByResidentSerialNumber(certificateConfirmationNumber, typeCode);
    }
}
