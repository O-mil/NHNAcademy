package com.nhnacademy.springbootaccountopen;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AccountOpenController {

    private final RestTemplate restTemplate;

    @Value("${ip.port}")
    private String ipPort;

    @GetMapping("/accounts")
    public String getAccounts() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity("parameters", httpHeaders);

        ResponseEntity response = restTemplate.exchange(ipPort + "/accounts", HttpMethod.GET, entity, String.class);

        return (String) response.getBody();
    }

    @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable("id") Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity("parameters", httpHeaders);

        ResponseEntity<Account> response = restTemplate.exchange(ipPort + "/accounts/" + id, HttpMethod.GET, entity, Account.class);

        return response.getBody();
    }

    @PostMapping("/accounts")
    public Account registerAccount(@RequestBody Account account) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(account.getId()));
        params.put("number", account.getNumber());
        params.put("balance", String.valueOf(account.getBalance()));

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(params, httpHeaders);

        ResponseEntity<Account> response = restTemplate.exchange(ipPort + "/accounts", HttpMethod.POST, entity, Account.class);

        return response.getBody();
    }

    @DeleteMapping("/accounts/{id}")
    public String deleteAccount(@PathVariable("id") Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity("parameters", httpHeaders);

        ResponseEntity response = restTemplate.exchange(ipPort + "/accounts/" + id, HttpMethod.DELETE, entity, String.class);

        return "{\"result\":\"OK\"}";
    }
}
