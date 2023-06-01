package com.nhnacademy.springbootstudent;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Student {

    @Id
    private Long id;
    private String name;
    private Integer score;


    public Student() {

    }

    public Student(Long id, String name, Integer score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }
}
