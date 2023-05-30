package com.example.certification.repository;

import com.example.certification.entity.QCertificateIssue;
import com.example.certification.entity.QResident;
import com.querydsl.core.types.Projections;
import com.example.certification.domain.CertificateIssueDTO;
import com.example.certification.entity.CertificateIssue;
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
