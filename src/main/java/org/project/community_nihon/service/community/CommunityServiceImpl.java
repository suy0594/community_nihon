package org.project.community_nihon.service.community;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.domain.community.Community;
import org.project.community_nihon.domain.user.UserVO;
import org.project.community_nihon.domain.utility.Certification;
import org.project.community_nihon.dto.community.CommunityDTO;
import org.project.community_nihon.repository.community.CommunityRepository;
import org.project.community_nihon.repository.user.UserRepository;
import org.project.community_nihon.repository.utility.CertificationRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;
    private final CertificationRepository certificationRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Community createCommunity(CommunityDTO communityDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<UserVO> userVO = userRepository.findById(authentication.getName());

        // 커뮤니티 엔티티 생성
        Community community = Community.builder()
                .title(communityDTO.getTitle())
                .is_group(true)
                .origin_member(userVO.get().getOrigin())
                .build();

        // 커뮤니티 엔티티 저장
        Community result = communityRepository.save(community);

        // 인증 엔티티 생성
        Certification certification = Certification.builder()
                .community(result)
                .master(userVO.get().getOrigin())
                .build();

        // 인증 엔티티 저장
        certificationRepository.save(certification);

        return result;
    }
}