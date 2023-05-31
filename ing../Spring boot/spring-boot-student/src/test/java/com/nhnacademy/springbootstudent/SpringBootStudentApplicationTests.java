package com.nhnacademy.springbootstudent;

import com.nhnacademy.springbootstudent.account.Account;
import com.nhnacademy.springbootstudent.account.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class SpringBootStudentApplicationTests {

	@Autowired
	AccountService accountService;

	@Test
	void contextLoads() {
		List< Account> actual = accountService.getAccounts();

		System.out.println(actual);
		assertThat(actual.size()).isEqualTo(2);
	}

}
