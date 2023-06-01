package com.nhnacademy.springbootaccountopen;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Controller
@RequiredArgsConstructor
public class AccountOpenWebController {
    private final RestTemplate restTemplate;

    @Value("${ip.port}")
    private String ipPort;

    @GetMapping("/web/accounts/{id}")
    public Account getAccount(@PathVariable Long id, Model model) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity("parameters", httpHeaders);

        ResponseEntity<Account> response = restTemplate.exchange(
                ipPort + "/web/accounts/" + id, HttpMethod.GET, entity, Account.class);

        return response.getBody();
    }
}
