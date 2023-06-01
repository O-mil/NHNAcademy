package com.nhnacademy.springbootaccount;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class DefaultAccountServiceTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    void getAccounts() {
        Account one = new Account(1L, "3333", 10000);
        accountRepository.save(one);

        Optional<Account> actual = accountRepository.findById(1L);
        assertThat(actual).isPresent();
        assertThat(actual.get()).isEqualTo(one);
    }
}