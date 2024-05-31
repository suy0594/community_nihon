package org.project.community_nihon.repository.board;

import org.project.community_nihon.domain.board.ReplyBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyBoardRepository extends JpaRepository<ReplyBoard, String> {

    @Query("SELECT r FROM ReplyBoard r WHERE r.board.id = :postId")
    List<ReplyBoard> getReplyBoardsByBoard_Id(Long postId);

}
