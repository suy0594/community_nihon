package org.project.community_nihon.service.user;

import org.project.community_nihon.dto.user.UserVODTO;

public interface UserService {

    static class IdExistException extends Exception {}

    UserVODTO registerUser(String id);
    String login(String userId, String password);

    void join(UserVODTO userVODTO) throws IdExistException;

}
