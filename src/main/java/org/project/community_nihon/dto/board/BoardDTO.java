package org.project.community_nihon.dto.board;

import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.project.community_nihon.domain.account.Account;

@Data
public class BoardDTO {

    private String content;

}
