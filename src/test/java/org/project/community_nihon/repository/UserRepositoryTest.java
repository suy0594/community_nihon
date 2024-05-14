package org.project.community_nihon.repository;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.project.community_nihon.domain.Account;
import org.project.community_nihon.domain.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void userInsertTest() {

        IntStream.rangeClosed(2, 20).forEach(i -> {

            Account account = Account.builder().id(Long.valueOf(i)).build();

            UserVO userVO = UserVO.builder()
                    .origin(account)
                    .email("aaa" + i + "@naver.com")
                    .password("111pw" + i)
                    .phone("010-0000-000phone" + i)
                    .screen_name("profile" + i)
                    .build();

            UserVO result = userRepository.save(userVO);
        });

    }

    @Test
    @Transactional
    public void userSelectTest() {

        int i = (int)(Math.random() * 20) + 1;

        Optional<UserVO> result = userRepository.findById(Long.valueOf(i));

        UserVO userVO = result.orElseThrow();

        log.info(userVO);


    }

    @Test
    public void userUpdateTest() {

        Optional<UserVO> result = userRepository.findById(Long.valueOf(1));

        UserVO userVO = result.orElseThrow();

        userVO.changeEmail("aaa1@naver.com");
        userVO.changePassword("111pw1");
        userVO.changePhone("010-0000-000phone1");

        userRepository.save(userVO);


    }


}
