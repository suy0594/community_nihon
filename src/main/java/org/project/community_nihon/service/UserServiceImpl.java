package org.project.community_nihon.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.project.community_nihon.domain.Account;
import org.project.community_nihon.domain.UserRole;
import org.project.community_nihon.domain.UserVO;
import org.project.community_nihon.dto.UserJoinDTO;
import org.project.community_nihon.repository.AccountRepository;
import org.project.community_nihon.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void join(UserJoinDTO userJoinDTO) throws IdExistException {

        String id = userJoinDTO.getId();

        boolean exist = userRepository.existsById(id);

        if (exist) {
            throw new IdExistException();
        }

        Account account = Account.builder().build();
        Account result = accountRepository.save(account);

        UserVO userVO = modelMapper.map(userJoinDTO, UserVO.class);
        userVO.changePassword(passwordEncoder.encode(userJoinDTO.getPassword()));
        userVO.addRole(UserRole.USER);
        userVO.addAccount(result);

        log.info("===========");
        log.info(userVO);
        log.info(userVO.getRoleSet());

        userRepository.save(userVO);


    }
}
