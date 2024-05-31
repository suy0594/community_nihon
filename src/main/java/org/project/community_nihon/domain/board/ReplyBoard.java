package org.project.community_nihon.domain.board;

import jakarta.persistence.*;
import lombok.*;
import org.project.community_nihon.domain.account.Account;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account origin;

    private String content;



}
