package org.project.community_nihon.repository.board;

import org.project.community_nihon.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("SELECT COUNT(b) FROM Board b WHERE b.community.community = :communityId")
    int countPostsByCommunityId(Long communityId);

    @Query("SELECT COUNT(b) FROM Board b WHERE b.origin.id = :id")
    int countPostsByAccountId(Long id);

}
