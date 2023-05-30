package com.nhnacademy.springbootstudent.account;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Account {


    @Id
    private Long id;
    private String number;
    private Integer balance;

    public Account() {

    }

    public Account(Long id, String number, Integer balance) {
        this.id = id;
        this.number = number;
        this.balance = balance;
    }
}
