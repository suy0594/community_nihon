package org.project.community_nihon.service.community;

import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.domain.community.Community;
import org.project.community_nihon.dto.board.BoardDTO;
import org.project.community_nihon.dto.community.CommunityDTO;

import java.util.List;

public interface CommunityService {

    CommunityDTO createCommunity(CommunityDTO communityDTO);
    List<BoardDTO> getBoardById(Long id);
    CommunityDTO getCommunity(Long id);
    List<CommunityDTO> getAllCommunities();


}
