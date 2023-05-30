package com.nhnacademy.springbootstudent.account;


import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DefaultAccountService implements AccountService {


    private final AccountRepository accountRepository;

    public DefaultAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }
}
