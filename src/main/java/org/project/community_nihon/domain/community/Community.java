package org.project.community_nihon.domain.community;

import jakarta.persistence.*;
import lombok.*;
import org.project.community_nihon.domain.BaseEntity_Created_Time;
import org.project.community_nihon.domain.account.Account;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Community extends BaseEntity_Created_Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long community;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account origin_member;

    private String title;

    private String description;

    private Boolean is_group;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOrigin_member(Account origin_member) {
        this.origin_member = origin_member;
    }

    public Account getOrigin_member() {
        return origin_member;
    }

    public String getTitle() {
        return title;
    }

    public Long getCommunity() {
        return this.community;
    }
}
