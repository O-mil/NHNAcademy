package com.example.certification.controller.restapi;


import com.example.certification.domain.BirthModifyDTO;
import com.example.certification.domain.BirthReportDTO;
import com.example.certification.service.BirthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/residents/birth")
public class BirthController {

    private final BirthService birthService;

    @PostMapping
    public ResponseEntity<BirthReportDTO> registerBirth(@PathVariable(name="serialNumber")Long serialNumber, @RequestBody BirthReportDTO birthReportDTO){
        BirthReportDTO res = birthService.registerBirth(serialNumber, birthReportDTO);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{targetSerialNumber}")
    public ResponseEntity<BirthModifyDTO> modifyBirth(@PathVariable(name="targetSerialNumber")Long targetSerialNumber,
                                                      @RequestBody BirthModifyDTO birthModifyDTO){
        BirthModifyDTO res = birthService.modifyBirth(targetSerialNumber, birthModifyDTO);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{targetSerialNumber}")
    public void deleteBirth(@PathVariable(name="targetSerialNumber")Long targetSerialNumber){
        birthService.deleteBirth(targetSerialNumber);
    }
}
