package org.project.community_nihon.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.project.community_nihon.domain.Account;
import org.project.community_nihon.domain.UserRole;
import org.project.community_nihon.domain.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class UserRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertTest() {

        IntStream.rangeClosed(1,20).forEach(i -> {
            Account account = Account.builder().build();
            accountRepository.save(account);

            UserVO userVO = UserVO.builder()
                    .id("user" + i)
                    .password(passwordEncoder.encode("1111"))
                    .email("email" + i + "@aaa.bbb")
                    .build();

            userVO.addRole(UserRole.USER);

            if (i >= 10) {
                userVO.addRole(UserRole.ADMIN);
            }
            userRepository.save(userVO);
        });

    }

    @Test
    public void selectTest() {

        Optional<UserVO> result = userRepository.getWithRoles("user20");

        UserVO userVO = result.orElseThrow();

        log.info(userVO);
        log.info(userVO.getRoleSet());

        userVO.getRoleSet().forEach(UserRole -> log.info(UserRole.name()));

    }

}
