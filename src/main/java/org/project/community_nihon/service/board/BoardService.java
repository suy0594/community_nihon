package org.project.community_nihon.service.board;

import org.project.community_nihon.domain.board.Board;
import org.project.community_nihon.domain.community.Community;
import org.project.community_nihon.dto.board.BoardDTO;

import java.util.List;

public interface BoardService {
    BoardDTO createBoard(BoardDTO boardDTO);
    List<BoardDTO> getBoardsByUserId(String userId);
    List<BoardDTO> getAllBoards();
    BoardDTO modifyBoard(Long boardId, String content);
    void deleteBoard(Long id);
    BoardDTO getBoardInfo(Long id);
}