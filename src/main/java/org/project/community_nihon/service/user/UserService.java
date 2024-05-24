package org.project.community_nihon.service.user;

import org.project.community_nihon.dto.user.UserVODTO;

public interface UserService {

    static class IdExistException extends Exception {}

    void join(UserVODTO userVODTO) throws IdExistException;

}
