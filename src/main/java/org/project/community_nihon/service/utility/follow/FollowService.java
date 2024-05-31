package org.project.community_nihon.service.utility.follow;

import org.project.community_nihon.domain.utility.Follow;
import org.project.community_nihon.dto.utility.FollowDTO;

import java.util.List;
import java.util.Optional;

public interface FollowService {

    FollowDTO createFollow(String id, FollowDTO followDTO);

    public List<FollowDTO> getAllFollows();

    public Optional<FollowDTO> getFollowById(Long id);

    public FollowDTO updateFollow(Long id, FollowDTO followDTO);

    void deleteFollow(String userId, String posterId);


}
