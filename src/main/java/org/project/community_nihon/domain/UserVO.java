package org.project.community_nihon.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@ToString(exclude = "roleSet")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO extends BaseEntity{

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account origin;

    private String screen_name;
    private String email;
    private String password;
    private String phone;
    private boolean del;
    private boolean social;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<UserRole> roleSet = new HashSet<>();

    public void addAccount(Account account) { this.origin = account; }

    public void addRole(UserRole userRole) {
        this.roleSet.add(userRole);
    }

    public void clearRoles() {
        this.roleSet.clear();
    }

    public void changeScreen_Name(String screen_name) {
        this.screen_name = screen_name;
    }

    public void changeEmail(String email) {
        this.email = email;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changePhone(String phone) {
        this.phone = phone;
    }

    public void changeDel(boolean del) {
        this.del = del;
    }

    public void changeSocial(boolean social) {
        this.social = social;
    }


}
