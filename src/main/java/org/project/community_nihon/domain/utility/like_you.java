package org.project.community_nihon.domain.utility;

import jakarta.persistence.*;
import lombok.*;
import org.project.community_nihon.domain.BaseEntity_Created_Time;
import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.domain.board.Board;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class like_you extends BaseEntity_Created_Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account origin;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    public void setOrigin(Account origin) {
        this.origin = origin;
    }
}
