package org.project.community_nihon.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.project.community_nihon.domain.account.Account;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserStatus {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account origin;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserVO user;

    private boolean open;
    private boolean banned;
    private boolean verification;



}
