package org.project.community_nihon.domain.user;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.domain.BaseEntity_Modified_Time;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfile extends BaseEntity_Modified_Time {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account origin;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserVO user;

    private String background_photo;
    private String photo;
    private String introduce;

}
