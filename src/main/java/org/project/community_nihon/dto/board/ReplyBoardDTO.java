package org.project.community_nihon.dto.board;

import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.domain.board.Board;

@Data
public class ReplyBoardDTO {

    private Long id;

    private Long BoardId;

    private Long origin;

    private String content;

    private String userId;

    private Long postId;

}
