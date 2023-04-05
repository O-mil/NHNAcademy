package com.nhnacamemy.student;

import java.util.Date;

public class Student {
    private String id;
    private String name;
    private Gender gender;
    private int age;
    private Date createdAt;    // 생성일

    public Student() {}

    public Student(String id, String name, Gender gender, int age) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.createdAt = new Date();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void update (String name, Gender gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student {" +
                "id = '" + id + '\'' +
                ", name = '" + name + '\'' +
                ", gender = " + gender +
                ", age = " + age +
                ", createdAt = " + createdAt +
                '}';
    }
}
