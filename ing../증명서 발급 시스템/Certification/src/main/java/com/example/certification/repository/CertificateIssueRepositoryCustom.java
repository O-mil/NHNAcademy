package com.example.certification.repository;

import com.example.certification.domain.CertificateIssueDTO;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CertificateIssueRepositoryCustom {

    CertificateIssueDTO getCertificateInfoByResidentSerialNumber(Long serialNumber, String typeCode);
}
