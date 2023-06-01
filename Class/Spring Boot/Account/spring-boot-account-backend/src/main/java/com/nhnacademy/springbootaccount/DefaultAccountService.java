package com.nhnacademy.springbootaccount;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class DefaultAccountService implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public Account createAccount(Account account) {
        accountRepository.findById(account.getId()).ifPresent(s -> {
            throw new RuntimeException("이미 존재하는 계좌입니다.");
        });
        return accountRepository.save(account);
    }

    @Override
    public Account getAccount(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 계좌입니다."));
    }

    @Override
    @Transactional
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

}
