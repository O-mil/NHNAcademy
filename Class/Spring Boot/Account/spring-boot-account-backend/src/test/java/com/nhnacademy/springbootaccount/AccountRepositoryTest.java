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
    }   // 다 찾음

    @Test
    void testFindById() {
        Account account = new Account(5L, "Test Account", 1000);
        entityManager.persist(account);
        entityManager.flush();

        Optional<Account> optionalAccount = accountRepository.findById(5L);
        assertThat(optionalAccount.isPresent()).isTrue();
        assertThat(optionalAccount.get().getNumber()).isEqualTo("Test Account");
        assertThat(optionalAccount.get().getBalance()).isEqualTo(1000);
    }   //아이디 맞으면 데려옴


    /*
    findAll:
        accountRepository.findAll() 메서드를 호출하여 모든 엔티티를 조회합니다.
        이 메서드는 데이터베이스의 해당 테이블에 있는 모든 레코드를 검색하고, 그 결과를 리스트 형태로 반환합니다.
        테스트 코드에서는 assertThat과 isEqualTo를 사용하여 반환된 결과가 기대하는 값인 account와 동일한지 확인합니다.
    findById:
        accountRepository.findById(5L) 메서드를 호출하여 주어진 식별자(여기서는 5L)에 해당하는 엔티티를 조회합니다.
        이 메서드는 데이터베이스에서 해당 식별자를 가진 레코드를 검색하고, 그 결과를 Optional 형태로 반환합니다.
        테스트 코드에서는 assertThat과 isTrue, isEqualTo를 사용하여 Optional이 존재하며, 반환된 엔티티의 속성들이 기대하는 값과 일치하는지 확인합니다.

    차이점:
        findAll은 테이블에 있는 모든 레코드를 조회하므로, 결과는 여러 개의 엔티티를 포함하는 리스트입니다.
        findById는 주어진 식별자에 해당하는 단일 엔티티를 조회하므로, 결과는 Optional 형태로 반환됩니다.
        findAll은 반환 타입이 List<T>이고, findById는 반환 타입이 Optional<T>입니다.
        findAll은 검색 조건이 없으므로, 특정 조건에 해당하는 엔티티를 조회할 수 없습니다.
        findById는 식별자를 기반으로 검색하므로, 특정 식별자에 해당하는 엔티티를 조회할 수 있습니다.
    */

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