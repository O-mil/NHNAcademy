package com.nhnacademy.springbootstudent.account;

import java.util.List;

public class DummyAccountRepository implements AccountRepository {

    @Override
    public List<Account> findAll() {
        return List.of(new Account("3333", 1000),
                new Account("302", 10000));
    }
}
