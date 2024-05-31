package org.project.community_nihon.domain.user;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account origin;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserVO user;

    @NotNull
    private long userIdx;
    @NotNull
    private String originalFileName;
    @NotNull
    private String storedFileName;

    private long fileSize;

}
