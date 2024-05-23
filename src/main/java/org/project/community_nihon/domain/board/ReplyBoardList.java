package org.project.community_nihon.domain.board;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyBoardList {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ReplyBoard reply;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;




}
