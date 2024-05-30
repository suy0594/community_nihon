package org.project.community_nihon.dto.utility;

import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.domain.board.Board;

@Data
public class like_youDTO {

    private Long id;

    private Account origin;

    private Board board;

}
