package org.project.community_nihon.security.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.project.community_nihon.domain.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
@ToString
public class UserSecurityDTO extends User {

    private String id;

    private String screen_name;
    private String email;
    private String password;
    private String phone;
    private boolean del;
    private boolean social;

    public UserSecurityDTO(String username, String password, String email,
                           boolean del, boolean social,
                           Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);

        this.id = username;
        this.password = password;
        this.email = email;
        this.del = del;
        this.social = social;
    }

}
