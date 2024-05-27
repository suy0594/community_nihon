package org.project.community_nihon.repository.community;

import org.project.community_nihon.domain.community.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Long> {
}
