package org.project.community_nihon.dto.user;

import lombok.Data;
import org.project.community_nihon.domain.account.Account;

@Data
public class UserVODTO {

    private Account origin;  // 외래키

    private String id;    // 기본키

    private String password;
    private String email;
    private String phone;
    private String screen_name;
    private boolean del;
    private boolean social;



}
