package com.nhnacademy.springbootstudent;

import com.nhnacademy.springbootstudent.account.Account;
import com.nhnacademy.springbootstudent.account.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DefaultAccountServiceTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    void testgetAccounts() {
        Account one = new Account(1L, "3333", 1000);
        accountRepository.save(one);

        Optional<Account> actual = accountRepository.findById(1L);
        assertThat(actual).isPresent();
        assertThat(actual.get()).isEqualTo(one);
    }
}