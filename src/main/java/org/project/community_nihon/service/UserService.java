package org.project.community_nihon.service;

import org.project.community_nihon.dto.UserJoinDTO;

public interface UserService {

    static class IdExistException extends Exception {}

    void join(UserJoinDTO userJoinDTO) throws IdExistException;

}
