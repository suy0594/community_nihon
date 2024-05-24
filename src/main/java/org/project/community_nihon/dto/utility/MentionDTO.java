package org.project.community_nihon.dto.utility;

import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.domain.board.Board;

@Data
public class MentionDTO {

    private Long id;

    private Board board;

    private Account from;

    private Account to;

}
