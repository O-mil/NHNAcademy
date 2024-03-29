package com.example.certification.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BirthReportDTO {

    private Long residentSerialNumber;
    private String birthDeathTypeCode;
    private LocalDateTime birthDeathReportDate;
    private String birthReportQualificationsCode;
    private String emailAddress;
    private String phoneNumber;
}
