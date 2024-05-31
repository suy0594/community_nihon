package org.project.community_nihon.service.board;

import org.project.community_nihon.dto.board.ReplyBoardDTO;

import java.util.List;

public interface ReplyService {

    ReplyBoardDTO createReply(ReplyBoardDTO replyBoardDTO);
    List<ReplyBoardDTO> getReplyByBoardId(Long postId);

}
