package com.example.certification.controller.restapi;


import lombok.RequiredArgsConstructor;
import org.example.domain.FamilyRelationModifyDTO;
import org.example.domain.FamilyRelationshipDTO;
import org.example.domain.FamilyRelationshipReportDTO;
import org.example.service.FamilyRelationshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/residents/{serialNumber}/relationship")
public class FamilyRelationshipController {

    private final FamilyRelationshipService familyRelationshipService;

    @PostMapping
    public ResponseEntity<FamilyRelationshipReportDTO> registRelation(@PathVariable(name="serialNumber")Long serialNumber, @RequestBody FamilyRelationshipDTO familyRelationshipDTO) {
        FamilyRelationshipDTO res = familyRelationshipService.registerFamilyRelationship(serialNumber, familyRelationshipDTO);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{familySerialNumber}")
    public ResponseEntity<FamilyRelationModifyDTO> modifyRelation(@PathVariable(name="serialNumber") Long serialNumber,
                                                                  @PathVariable(name="familySerialNumber") Long familySerialNumber,
                                                                  @RequestBody FamilyRelationModifyDTO familyRelationshipDTO) {
        FamilyRelationModifyDTO res = familyRelationshipService.modifyFamilyRelationship(familyRelationshipDTO, familySerialNumber, serialNumber);

        return ResponseEntity.ok(res);
    }

}
