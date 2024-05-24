package org.project.community_nihon.service.community;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.project.community_nihon.domain.community.Community;
import org.project.community_nihon.dto.community.CommunityDTO;
import org.project.community_nihon.repository.community.CommunityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;
    private final ModelMapper modelMapper;

    @Override
    public CommunityDTO getCommunity(String id) {
        Community communityEntity = communityRepository.findById(id).orElseThrow(() -> new RuntimeException("Community not found"));
        return modelMapper.map(communityEntity, CommunityDTO.class);
    }

    @Override
    public List<CommunityDTO> getAllCommunities() {
        return communityRepository.findAll().stream()
                .map(community -> modelMapper.map(community, CommunityDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public String createCommunity(CommunityDTO communityDTO) {
        Community community = modelMapper.map(communityDTO, Community.class);
        Community result = communityRepository.save(community);
        return result.getCommunity();
    }

    @Override
    public void modifyCommunity(String id) {
        Optional<Community> community = communityRepository.findById(id);
        if(community.isPresent()) {
            community.get().setTitle(community.get().getTitle());
            community.get().setOrigin_master(community.get().getOrigin_master());
            community.get().setOrigin_member(community.get().getOrigin_member());
            communityRepository.save(community.get());
        } else {
            throw new IllegalArgumentException("No community with the given id found");
        }
    }

    @Override
    public void deleteCommunity(String id) {
        Optional<Community> communityEntity = communityRepository.findById(id);
        if(communityEntity.isPresent()) {
            communityRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No community with the given id found");
        }
    }
}