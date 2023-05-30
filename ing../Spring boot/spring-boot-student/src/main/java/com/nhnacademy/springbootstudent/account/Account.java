package com.nhnacademy.springbootstudent.account;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class Account {

    private final String number;
    private final Integer balance;
}
