package org.project.community_nihon.repository.board;

import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("SELECT COUNT(b) FROM Board b WHERE b.community.community = :communityId")
    int countPostsByCommunityId(Long communityId);

    @Query("SELECT b FROM Board b WHERE b.community.community = :id")
    List<Board> findByCommunityId(Long id);

    @Query("SELECT COUNT(b) FROM Board b WHERE b.origin.id = :id")
    int countPostsByAccountId(Long id);

    @Query("SELECT b FROM Board b WHERE b.origin = :origin")
    List<Board> findByOrigin(Account origin);

}
