package com.nhnacademy.springbootaccountopen;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountService {

   private final RestTemplate restTemplate;

    @Value("${ip.port}")
    private String ipPort;

    public List<Account> findAll() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<Account>> exchange = restTemplate.exchange(ipPort,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<Account>>() {
                });
        return exchange.getBody();
    }
}
