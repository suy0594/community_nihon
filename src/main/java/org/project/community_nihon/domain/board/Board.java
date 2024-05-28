package org.project.community_nihon.domain.board;

import jakarta.persistence.*;
import lombok.*;
import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.domain.BaseEntity_Created_Time;
import org.project.community_nihon.domain.community.Community;


@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board extends BaseEntity_Created_Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account origin;

    @ManyToOne(fetch = FetchType.LAZY)
    private Community community;

    private String content;

    public void updateContent(String content) {
        this.content = content;
    }
    public void updateAccount(Account origin) {
        this.origin = origin;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }
}
