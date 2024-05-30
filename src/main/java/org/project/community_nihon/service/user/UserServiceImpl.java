package org.project.community_nihon.service.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.domain.community.Community;
import org.project.community_nihon.domain.user.UserRole;
import org.project.community_nihon.domain.user.UserVO;
import org.project.community_nihon.domain.utility.Certification;
import org.project.community_nihon.dto.community.CommunityDTO;
import org.project.community_nihon.dto.user.UserVODTO;
import org.project.community_nihon.dto.utility.FollowDTO;
import org.project.community_nihon.repository.account.AccountRepository;
import org.project.community_nihon.repository.community.CommunityRepository;
import org.project.community_nihon.repository.user.UserRepository;
import org.project.community_nihon.repository.utility.CertificationRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;


    private final CommunityRepository communityRepository;

    private final CertificationRepository certificationRepository;

    @Transactional
    @Override
    public String login(String userId, String password) {
        Optional<UserVO> userVOOptional = userRepository.findById(userId);
        if (userVOOptional.isPresent()) {
            UserVO user = userVOOptional.get();
            if (user.getPassword().equals(password)) {
                return "success";
            }
        }
        return "fail";
    }

    @Override
    public void join(UserVODTO userVODTO) throws IdExistException {

        String id = userVODTO.getId();

        boolean exist = userRepository.existsById(id);

        if (exist) {
            throw new IdExistException();
        }

        Account account = Account.builder().build();
        Account result = accountRepository.save(account);

        UserVO userVO = modelMapper.map(userVODTO, UserVO.class);
        userVO.addRole(UserRole.USER);
        userVO.addAccount(result);


        log.info("===========");
        log.info(userVO);
        log.info(userVO.getRoleSet());

        userRepository.save(userVO);

        Community community = Community.builder()
                .title(userVO.getId())
                .origin_member(userVO.getOrigin())
                .is_group(false)
                .build();

        communityRepository.save(community);

        Certification certification = Certification.builder()
                .master(userVO.getOrigin())
                .community(community)
                .build();

        certificationRepository.save(certification);

    }
}
