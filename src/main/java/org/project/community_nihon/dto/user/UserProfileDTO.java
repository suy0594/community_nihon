package org.project.community_nihon.dto.user;

import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.domain.user.UserVO;

@Data
public class UserProfileDTO {

    private String id;  // 기본키

    private Account origin;  // 외래키

    private UserVO user;  // 외래키

    private String background_photo;
    private String photo;
    private String introduce;

}
