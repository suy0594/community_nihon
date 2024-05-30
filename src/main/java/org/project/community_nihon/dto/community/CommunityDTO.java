package org.project.community_nihon.dto.community;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.project.community_nihon.domain.account.Account;

@Data

public class CommunityDTO {

    private Account origin_master;

    private Account origin_member;

    private String title;

    private Boolean is_group;

}
