package org.project.community_nihon.dto.utility;

import lombok.Data;
import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.domain.board.Board;

@Data
public class BookmarkDTO {

    private Long id;

    private Account origin;

    private Board board;





}
