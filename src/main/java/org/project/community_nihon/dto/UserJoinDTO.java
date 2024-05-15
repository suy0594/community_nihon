package org.project.community_nihon.dto;

import lombok.Data;

@Data
public class UserJoinDTO {

    private String id;

    private String password;
    private String email;
    private String phone;
    private String screen_name;
    private boolean del;
    private boolean social;



}
