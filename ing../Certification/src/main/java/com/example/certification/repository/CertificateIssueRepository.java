package com.example.certification.repository;

import org.example.entity.CertificateIssue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateIssueRepository extends JpaRepository<CertificateIssue, Long>, CertificateIssueRepositoryCustom {
}
