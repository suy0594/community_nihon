package org.project.community_nihon.service.user;

import org.project.community_nihon.dto.board.BoardDTO;
import org.project.community_nihon.dto.user.UserVODTO;

import java.util.List;

public interface UserService {

    static class IdExistException extends Exception {}
    List<BoardDTO> registerUser2(String id);
    UserVODTO registerUser(String id);
    String login(String userId, String password);

    void join(UserVODTO userVODTO) throws IdExistException;

}
