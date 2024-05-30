package org.project.community_nihon.repository.community;

import org.project.community_nihon.domain.community.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommunityRepository extends JpaRepository<Community, Long> {

    @Query("SELECT n from Community n where n.title = :title")
    Community getCommunityByTitle(String title);

    @Query("SELECT COUNT(a) FROM Community c JOIN c.origin_member a WHERE c.community = :communityId")
    Long countMembersByCommunityId(@Param("communityId") Long communityId);

}
