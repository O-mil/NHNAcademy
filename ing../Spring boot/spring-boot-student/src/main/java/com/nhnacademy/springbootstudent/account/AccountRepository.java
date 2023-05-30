package com.nhnacademy.springbootstudent.account;

import java.util.List;

public interface AccountRepository {
    List<Account> findAll();
}
