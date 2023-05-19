package com.example.certification.controller.restapi;


import lombok.RequiredArgsConstructor;
import org.example.domain.ResidentModifyDTO;
import org.example.domain.ResidentRegisterDTO;
import org.example.entity.Resident;
import org.example.service.ResidentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/residents")
public class ResidentController {

    private final ResidentService residentService;

    @PostMapping
    public Resident registerResident(@RequestBody ResidentRegisterDTO residentRegisterDTO) {
        return residentService.register(residentRegisterDTO);
    }

    @PutMapping("/{serialNumber}")
    public Resident modifyResident(@PathVariable(name="serialNumber") Long serialNumber, @RequestBody ResidentModifyDTO residentModifyDTO) {
        return residentService.modify(serialNumber, residentModifyDTO);
    }
}
