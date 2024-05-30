package org.project.community_nihon.service.community;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.domain.community.Community;
import org.project.community_nihon.domain.user.UserVO;
import org.project.community_nihon.domain.utility.Certification;
import org.project.community_nihon.dto.community.CommunityDTO;
import org.project.community_nihon.repository.board.BoardRepository;
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

    private final BoardRepository boardRepository;
    private final CommunityRepository communityRepository;
    private final CertificationRepository certificationRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public CommunityDTO createCommunity(CommunityDTO communityDTO) {

        UserVO userVO = userRepository.findById(communityDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + communityDTO.getUserId()));

        // 커뮤니티 엔티티 생성
        Community community = Community.builder()
                .title(communityDTO.getTitle())
                .description(communityDTO.getDescription())
                .is_group(true)
                .origin_member(userVO.getOrigin())
                .build();

        // 커뮤니티 엔티티 저장
        Community result = communityRepository.save(community);

        // 인증 엔티티 생성
        Certification certification = Certification.builder()
                .community(result)
                .master(userVO.getOrigin())
                .build();

        // 인증 엔티티 저장
        certificationRepository.save(certification);



        CommunityDTO communityDTO1 = new CommunityDTO();

        communityDTO1.setId(community.getCommunity());
        communityDTO1.setTitle(result.getTitle());
        communityDTO1.setDescription(result.getDescription());
        communityDTO1.setNumber_of_member(0);
        communityDTO1.setNumber_of_posts(0);
        communityDTO1.setIs_group(true);
        communityDTO1.setUserId(userVO.getId());

        return communityDTO1;
    }

    public List<CommunityDTO> getAllCommunities() {
        List<Community> communities = communityRepository.findAll();
        return communities.stream()
                .filter(Community::getIs_group) // is_group이 true인 경우만 포함
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CommunityDTO convertToDTO(Community community) {
        CommunityDTO communityDTO = new CommunityDTO();
        communityDTO.setId(community.getCommunity());
        communityDTO.setTitle(community.getTitle());
        communityDTO.setDescription(community.getDescription());
        communityDTO.setIs_group(community.getIs_group());

        // Count members and posts for each community
        Long numberOfMembers = communityRepository.countMembersByCommunityId(community.getCommunity());
        int numberOfPosts = boardRepository.countPostsByCommunityId(community.getCommunity());

        communityDTO.setNumber_of_member(numberOfMembers != null ? numberOfMembers.intValue() : 0);
        communityDTO.setNumber_of_posts(numberOfPosts);

        return communityDTO;
    }


}