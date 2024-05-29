package org.project.community_nihon.service.board;

import org.project.community_nihon.domain.board.Board;
import org.project.community_nihon.domain.community.Community;
import org.project.community_nihon.dto.board.BoardDTO;

import java.util.List;

public interface BoardService {
    BoardDTO createBoard(String userId, String content, String title);
    BoardDTO getBoard(Long id);
    List<BoardDTO> getAllBoards();
    String modifyBoard(Long BoardId,String content);
    void deleteBoard(Long id);
}