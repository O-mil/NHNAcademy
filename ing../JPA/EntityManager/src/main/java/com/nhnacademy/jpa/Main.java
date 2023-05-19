package com.nhnacademy.jpa;

import com.nhnacademy.jpa.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        User user = new User();
        user.setName("홍길동");

        EntityManagerFactory enf = Persistence.createEntityManagerFactory("default");

        EntityManager entityManager = enf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();

        entityManager.close();

        enf.close();
    }
}
