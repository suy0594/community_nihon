package org.project.community_nihon.dto.board;

import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.domain.community.Community;

import java.time.LocalDateTime;

@Data
public class BoardDTO {

    private Long id;  // 게시판 아이디

    private Long origin;   // 작성자의 계정 식별

    private String userId;  // 유저 아이디

    private String title;  // 커뮤니티

    private String content;  // 내용

    private int like;

    private String posterId;

    private String created_time;

    private int likes;

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
