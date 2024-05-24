package org.project.community_nihon.dto.board;

import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.domain.community.Community;

@Data
public class BoardDTO {

    private Long id;

    private Account origin;

    private Community community;

    private String content;

}
