package com.nhnacademy.springbootstudent;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system")
public class VersionController {

    @Value("${com.nhn.account.system.version}")
    private String version;

    @GetMapping("/version")
    public VersionResponse getVersion() {
        VersionResponse response = new VersionResponse();
        response.setVersion(version);
        return response;
    }

}
