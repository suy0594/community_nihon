package org.project.community_nihon.dto.utility;

import lombok.Data;
import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.domain.community.Community;

@Data
public class CertificationDTO {

    private Long id;

    private Community community;

    private Account origin;

    private Boolean certified;
    private Boolean master;

}
