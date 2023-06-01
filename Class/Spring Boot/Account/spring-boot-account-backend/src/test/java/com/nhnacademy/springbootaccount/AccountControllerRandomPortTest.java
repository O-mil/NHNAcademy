package com.nhnacademy.springbootaccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountControllerRandomPortTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Order(1)
    void testGetStudents() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Account> entity = new HttpEntity<>(headers);
        ResponseEntity<List<Account>> exchange = testRestTemplate.exchange(
                "/accounts",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Account>>() {
                });

        assertThat(exchange.getBody())
                .contains(new Account(1L, "1111", 20000));
    }

    @Test
    @Order(2)
    void testGetStudent() throws Exception{
        ResponseEntity<Account> result = testRestTemplate.getForEntity(
                "/accounts/{id}",
                Account.class,
                1L);

        assertThat(result.getBody())
                .isEqualTo(new Account(1L, "1111", 20000));
    }

    @Test
    @Order(3)
    void testCreateStudent() throws Exception{
        Account account = new Account(4L, "4444", 67000);
        ResponseEntity<Account> result = testRestTemplate.postForEntity(
                "/accounts",
                account,
                Account.class);

        assertThat(result.getBody())
                .isEqualTo(account);
    }

    @Test
    @Order(4)
    void testDeleteStudent() throws Exception{
        testRestTemplate.delete(
                "/accounts/{id}",
                4L);
    }

}