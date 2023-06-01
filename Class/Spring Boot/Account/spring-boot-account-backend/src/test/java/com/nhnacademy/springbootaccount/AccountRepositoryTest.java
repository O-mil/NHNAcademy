package com.nhnacademy.springbootaccount;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    AccountRepository accountRepository;

    @Test
    void testFindAll() throws Exception {
        Account account = new Account(5L, "생활비", 100000);

        this.entityManager.merge(account);
        Account actual = accountRepository.findById(5L).orElse(null);

        assertThat(actual).isEqualTo(account);
    }

    @Test
    void testFindById() {
        Account account = new Account(5L, "Test Account", 1000);
        entityManager.persist(account);
        entityManager.flush();

        Optional<Account> optionalAccount = accountRepository.findById(5L);
        assertThat(optionalAccount.isPresent()).isTrue();
        assertThat(optionalAccount.get().getNumber()).isEqualTo("Test Account");
        assertThat(optionalAccount.get().getBalance()).isEqualTo(1000);
    }

    @Test
    void testSave() {
        Account account = new Account(5L, "save Account", 2000);

        Account savedAccount = accountRepository.save(account);
        entityManager.flush();

        Optional<Account> optionalAccount = accountRepository.findById(5L);
        assertThat(optionalAccount.isPresent()).isTrue();
        assertThat(optionalAccount.get().getNumber()).isEqualTo("save Account");
        assertThat(optionalAccount.get().getBalance()).isEqualTo(2000);
    }

    @Test
    void testDelete() {
        Account account = new Account(5L, "delete Account", 3000);
        entityManager.persist(account);
        entityManager.flush();

        accountRepository.delete(account);
        entityManager.flush();

        Optional<Account> optionalAccount = accountRepository.findById(5L);
        assertThat(optionalAccount.isPresent()).isFalse();
    }






}