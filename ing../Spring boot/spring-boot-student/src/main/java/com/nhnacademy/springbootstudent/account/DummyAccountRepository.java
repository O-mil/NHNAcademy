package com.nhnacademy.springbootstudent.account;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DummyAccountRepository implements AccountRepository {

    @Override
    public List<Account> findAll() {
        return List.of(new Account("3333", 1000),
                new Account("302", 10000));
    }
}
