package org.project.community_nihon.service.utility.follow;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.domain.user.UserVO;
import org.project.community_nihon.domain.utility.Follow;
import org.project.community_nihon.dto.utility.FollowDTO;
import org.project.community_nihon.repository.account.AccountRepository;
import org.project.community_nihon.repository.user.UserRepository;
import org.project.community_nihon.repository.utility.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class FollowServiceImpl {

    private final FollowRepository followRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public FollowDTO createFollow(String id, FollowDTO followDTO) {

        log.info("service: " + followDTO);

        UserVO userVO = userRepository.findById(id)  // 2
                .orElseThrow(() -> new RuntimeException("Origin account not found"));
        UserVO userVO1 = userRepository.findById(followDTO.getFollow())  // 1
                .orElseThrow(() -> new RuntimeException("Follow account not found"));

          // 1이 팔로우 중인 사람

        if (!followRepository.existsByOriginAndFollows(userVO1.getOrigin(), userVO.getOrigin())) {  // 1이 2 팔로우 중인지

            Follow followEntity = Follow.builder()
                    .origin(userRepository.findAccountByUserId(userVO.getId()))
                    .follow(userRepository.findAccountByUserId(userVO1.getId()))
                    .friend(false)
                    .build();
            followRepository.save(followEntity);
        }
        else {
            Follow follow = followRepository.findByOriginAndFollow(userVO1.getOrigin(), userVO.getOrigin());
            follow.setFriend(true);
            followRepository.save(follow);
        }

        FollowDTO followDTO1 = new FollowDTO();
        followDTO1.setFollower_count(followRepository.countOriginsByFollow(userVO1.getOrigin()) +
                                        followRepository.countFriendFollowsByOrigin(userVO1.getOrigin()));
        followDTO1.setFollowing_count(followRepository.countFollowsByOrigin(userVO1.getOrigin()) +
                                        followRepository.countFriendOriginsByFollow(userVO1.getOrigin()));
        return followDTO1;

    }

    @Transactional
    public FollowDTO getFollow(Long id) {
        Follow follow = followRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Follow not found"));
        return modelMapper.map(follow, FollowDTO.class);
    }

    @Transactional
    public FollowDTO updateFollow(Long id, FollowDTO followDTO) { /*
        Follow follow = followRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Follow not found"));

        Account origin = accountRepository.findById(followDTO.getOrigin().getId())
                .orElseThrow(() -> new RuntimeException("Origin account not found"));
        Account followAccount = accountRepository.findById(followDTO.getFollow().getId())
                .orElseThrow(() -> new RuntimeException("Follow account not found"));

        follow.setOrigin(origin);
        follow.setFollow(followAccount);
        follow.setFriend(followDTO.isFriend());

        Follow updatedFollow = followRepository.save(follow);
        return modelMapper.map(updatedFollow, FollowDTO.class); */
        return new FollowDTO();
    }

    @Transactional
    public void deleteFollow(Long id) {
        followRepository.deleteById(id);
    }
}