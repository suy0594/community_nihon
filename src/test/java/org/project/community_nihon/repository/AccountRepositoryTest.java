package org.project.community_nihon.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.project.community_nihon.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void accountInsertTest() {

        IntStream.rangeClosed(1, 19).forEach(i -> {
            Account account = Account.builder().build();
            Account result = accountRepository.save(account);
        });
    }

    @Test
    public void selectAccountTest() {

        int i = (int) (Math.random() * 20) + 1;

        Optional<Account> result = accountRepository.findById(Long.valueOf(i));

        Account account = result.orElseThrow();

        log.info(account);


    }


}
