package com.example.certification.repository;

import com.querydsl.core.types.Projections;
import org.example.domain.CertificateIssueDTO;
import org.example.entity.CertificateIssue;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class CertificateIssueRepositoryImpl extends QuerydslRepositorySupport implements CertificateIssueRepositoryCustom {

    public CertificateIssueRepositoryImpl() {
        super(CertificateIssue.class);
    }

    @Override
    public CertificateIssueDTO getCertificateInfoByResidentSerialNumber(Long serialNumber, String typeCode) {
        QCertificateIssue certificateIssue = QCertificateIssue.certificateIssue;
        QResident resident = QResident.resident;

        return from(certificateIssue)
                .select(Projections.constructor(CertificateIssueDTO.class,
                        certificateIssue.certificateConfirmationNumber,
                        certificateIssue.certificateTypeCode,
                        certificateIssue.certificateIssueDate))
                .where((resident.residentSerialNumber.eq(serialNumber))
                        .and(certificateIssue.certificateTypeCode.eq(typeCode)))
                .fetchOne();
    }
}
