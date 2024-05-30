package org.project.community_nihon.repository.community;

import org.project.community_nihon.domain.community.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommunityRepository extends JpaRepository<Community, Long> {

    @Query("SELECT n from Community n where n.title = :title")
    Community getCommunityByTitle(String title);

}
