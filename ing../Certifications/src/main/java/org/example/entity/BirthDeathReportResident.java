package org.example.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table (name = "birth_death_report_resident")
public class BirthDeathReportResident {

    @EmbeddedId
    private Pk pk;

    @MapsId("residentSerialNumber")
    @ManyToOne
    @JoinColumn (name = "resident_serial_number")
    private Resident resident;

    @ManyToOne
    @JoinColumn (name = "report_resident_serial_number")
    private Resident reportResidentSerialNumber;

    @Column (name = "birth_death_report_date")
    private String birthDeathReportDate;

    @Column (name = "birth_report_qualifications_code")
    private String birthReportQualificationsCode;

    @Column (name = "death_report_qualifications_code")
    private String deathReportQualificationsCode;

    @Column (name = "email_address")
    private String emailAddress;

    @Column (name = "phone_number")
    private String phoneNumber;


    @Getter
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class Pk implements Serializable {
        @Column (name = "resident_serial_number")
        private Long residentSerialNumber;

        @Column (name = "birth_death_type_code")
        private String birthDeathTypeCode;
    }

    public void updateReportInfo (String emailAddress, String phoneNumber) {

        if (emailAddress != null) {
            this.emailAddress = emailAddress;
        }

        if (phoneNumber != null) {
            this.phoneNumber = phoneNumber;
        }
    }
}
