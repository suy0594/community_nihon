package org.project.community_nihon.dto.utility;

import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.dto.user.UserVODTO;

import java.util.List;

@Data
public class FollowDTO {

    private Long id;

    private Long origin;

    private String follow;

    private boolean friend;

    private String userId;

    private int follower_count;

    private int following_count;

    private List<UserVODTO> follower_list;

    private List<UserVODTO> following_list;

}
