package org.project.community_nihon.repository.utility;

import org.project.community_nihon.domain.utility.like_you;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikeYouRepository extends JpaRepository<like_you, Long> {

    @Query("SELECT l FROM like_you l JOIN l.board b WHERE b.id = :id")
    List<like_you> findByBoardId(@Param("id") Long id);

    @Query("SELECT count(l) FROM like_you l JOIN l.board b WHERE b.id = :id")
    int countlike_youByBoardId(Long id);

    // origin_id 이름으로 받기, <---- Account의 id임, id는 board의 id임
    @Query("SELECT l FROM like_you l JOIN l.board b JOIN l.origin o WHERE b.id = :id AND o.id = :origin_id")
    Optional<like_you> findByBoardIdAndOriginId(@Param("id") Long id, @Param("origin_id") Long origin_id);



}
