package org.project.community_nihon.domain.utility;

import jakarta.persistence.*;
import lombok.*;
import org.project.community_nihon.domain.account.Account;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account origin;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account follow;

    private boolean friend;


    public void setOrigin(Account origin) {
        this.origin = origin;
    }

    public void setFollow(Account follow) {
        this.follow = follow;
    }

    public void setFriend(boolean friend) {
        this.friend = friend;
    }
}
