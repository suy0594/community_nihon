package org.project.community_nihon.dto.community;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.project.community_nihon.domain.account.Account;

@Data

public class CommunityDTO {

    private Long id;

    private String userId;

    private int origin_member;

    private String title;

    private String description;

    private Boolean is_group;

    private int number_of_member;

    private int number_of_posts;


}
