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
public class Bookmark extends BaseEntity_Created_Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setOrigin(Account origin) {
        this.origin = origin;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Account origin;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

}
