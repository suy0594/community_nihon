package org.project.community_nihon.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.project.community_nihon.domain.UserVO;
import org.project.community_nihon.repository.UserRepository;
import org.project.community_nihon.security.dto.UserSecurityDTO;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
//@EnableGlobalMethodSecurity(prePostEnabled = true) // 어노테이션으로 권한 설정, pre~ : 원하는 곳에 PreAuthorize, PostAuthorize 어노테이션을 이용해서 사전 혹은 사후의 권한을 체크
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("loadUserByUsername: " + username);

        Optional<UserVO> result = userRepository.getWithRoles(username);

        if (result.isEmpty()) {
            throw new UsernameNotFoundException("username not found....");
        }

        UserVO userVO = result.get();

        UserSecurityDTO userSecurityDTO =
                new UserSecurityDTO(
                        userVO.getId(),
                        userVO.getPassword(),
                        userVO.getEmail(),
                        userVO.isDel(),
                        false,
                        userVO.getRoleSet()
                                .stream().map(userRole -> new SimpleGrantedAuthority(
                                        "ROLE_" + userRole.name()
                                )).collect(Collectors.toList())
                );

        log.info("userSecurityDTO");
        log.info(userSecurityDTO);

        return userSecurityDTO;

    }
}
