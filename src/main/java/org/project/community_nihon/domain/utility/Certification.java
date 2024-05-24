package org.project.community_nihon.domain.utility;

import jakarta.persistence.*;
import lombok.*;
import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.domain.community.Community;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Community community;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account origin;

    private Boolean certified;
    private Boolean master;


    public void setCertified(Boolean certified) {
        this.certified = certified;
    }

    public void setMaster(Boolean master) {
        this.master = master;
    }
}
