package org.project.community_nihon.dto.utility;

import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.project.community_nihon.domain.account.Account;

@Data
public class FollowDTO {

    private Long id;

    private Account origin;

    private Account follow;

    private boolean friend;

}
