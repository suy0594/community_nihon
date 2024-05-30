package org.project.community_nihon.service.utility.follow;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.domain.utility.Follow;
import org.project.community_nihon.dto.utility.FollowDTO;
import org.project.community_nihon.repository.account.AccountRepository;
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
    private final ModelMapper modelMapper;

    @Transactional
    public FollowDTO createFollow(FollowDTO followDTO) {

        log.info("service: " + followDTO);

        Account origin = accountRepository.findById(followDTO.getOrigin().getId())
                .orElseThrow(() -> new RuntimeException("Origin account not found"));
        Account follow = accountRepository.findById(followDTO.getFollow().getId())
                .orElseThrow(() -> new RuntimeException("Follow account not found"));

        Follow followEntity = new Follow();
        followEntity.setOrigin(origin);
        followEntity.setFollow(follow);
        followEntity.setFriend(followDTO.isFriend());

        Follow savedFollow = followRepository.save(followEntity);

        return modelMapper.map(savedFollow, FollowDTO.class);
    }

    @Transactional(readOnly = true)
    public FollowDTO getFollow(Long id) {
        Follow follow = followRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Follow not found"));
        return modelMapper.map(follow, FollowDTO.class);
    }

    @Transactional
    public FollowDTO updateFollow(Long id, FollowDTO followDTO) {
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
        return modelMapper.map(updatedFollow, FollowDTO.class);
    }

    @Transactional
    public void deleteFollow(Long id) {
        followRepository.deleteById(id);
    }
}