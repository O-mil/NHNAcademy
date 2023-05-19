package org.example.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table (name = "family_relationship")
public class FamilyRelationship {

    @EmbeddedId
    private Pk pk;

    @Column (name = "family_relationship_code")
    private String familyRelationshipCode;


    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Embeddable
    public static class Pk implements Serializable {

        @Column (name = "base_resident_serial_number")
        private Long baseResidentSerialNumber;

        @Column (name = "family_resident_serial_number")
        private Long familyResidentSerialNumber;
    }

    public void modifyRelationInfo(String relationship) {
        if (familyRelationshipCode != null) {
            familyRelationshipCode = relationship;
        }
    }
}
