package org.project.community_nihon.service.utility.follow;

import org.project.community_nihon.domain.utility.Follow;
import org.project.community_nihon.dto.utility.FollowDTO;

import java.util.List;
import java.util.Optional;

public interface FollowService {

    public FollowDTO createFollow(FollowDTO followDTO);

    public List<FollowDTO> getAllFollows();

    public Optional<FollowDTO> getFollowById(Long id);

    public FollowDTO updateFollow(Long id, FollowDTO followDTO);

    public void deleteFollow(Long id);


}
