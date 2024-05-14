package org.project.community_nihon.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString(exclude = "Account")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account origin;

    private String screen_name;
    private String email;
    private String password;
    private String phone;

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

}
