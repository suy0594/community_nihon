package org.project.community_nihon.service.community;

import org.project.community_nihon.dto.community.CommunityDTO;

import java.util.List;

public interface CommunityService {

    CommunityDTO getCommunity(String id);

    public List<CommunityDTO> getAllCommunities();

    String createCommunity(CommunityDTO communityDTO);

    void modifyCommunity(String id);

    void deleteCommunity(String id);


}
