package com.example.certification.repository;

import org.example.domain.CertificateIssueDTO;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CertificateIssueRepositoryCustom {

    CertificateIssueDTO getCertificateInfoByResidentSerialNumber(Long serialNumber, String typeCode);
}
