package org.project.community_nihon.service.board;

import org.project.community_nihon.dto.board.ReplyBoardDTO;

public interface ReplyService {

    ReplyBoardDTO createReply(ReplyBoardDTO replyBoardDTO);
    ReplyBoardDTO getReplyByBoardId(ReplyBoardDTO replyBoardDTO);

}
