package org.project.community_nihon.domain.board;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.domain.BaseEntity_Created_Time;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardMedia extends BaseEntity_Created_Time {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;                  // 게시글 ID

    @ManyToOne(fetch = FetchType.LAZY)      // 작성자 ID
    private Account origin;

    private String filename;
    private String format;


}
