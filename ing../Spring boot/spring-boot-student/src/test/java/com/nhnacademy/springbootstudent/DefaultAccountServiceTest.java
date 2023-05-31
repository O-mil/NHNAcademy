package com.nhnacademy.springbootstudent;

import com.nhnacademy.springbootstudent.account.Account;

import com.nhnacademy.springbootstudent.account.AccountService;

import com.nhnacademy.springbootstudent.account.AccountRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import javax.swing.text.html.Option;
import java.util.Optional;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DefaultAccountServiceTest {

    @Autowired
    AccountService accountService;
  
//     @Test
//     void getAccounts() {
//         List< Account> actual = accountService.getAccounts();

//         System.out.println(actual);

//     AccountRepository accountRepository;

    @Test
    void testgetAccounts() {
        Account one = new Account(1L, "3333", 1000);
        accountRepository.save(one);

        Optional<Account> actual = accountRepository.findById(1L);
        assertThat(actual).isPresent();

    }
}