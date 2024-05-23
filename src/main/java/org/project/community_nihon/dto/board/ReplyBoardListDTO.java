package org.project.community_nihon.dto.board;

import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.project.community_nihon.domain.board.Board;
import org.project.community_nihon.domain.board.ReplyBoard;

@Data
public class ReplyBoardListDTO {

    private String id;

}
