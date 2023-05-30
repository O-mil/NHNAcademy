package com.nhnacademy.springbootstudent;

import com.nhnacademy.springbootstudent.account.Account;
import com.nhnacademy.springbootstudent.account.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DefaultAccountServiceTest {

    @Autowired
    AccountService accountService;
    @Test
    void getAccounts() {
        List< Account> actual = accountService.getAccounts();

        assertThat(actual.size()).isEqualTo(2);
    }
}