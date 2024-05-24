package org.project.community_nihon.service.utility.like_you;

import org.project.community_nihon.dto.utility.like_youDTO;

import java.util.List;
import java.util.Optional;

public interface like_youService {

    public like_youDTO createLikeYou(like_youDTO likeYouDTO);

    public List<like_youDTO> getAllLikeYous();

    public Optional<like_youDTO> getLikeYouById(Long id);

    public void deleteLikeYou(Long id);

}
