package org.project.community_nihon.service.board;

import org.project.community_nihon.dto.board.BoardDTO;

import java.util.List;

public interface BoardService {
    void createBoard(BoardDTO boardDTO);
    BoardDTO getBoard(String id);
    List<BoardDTO> getAllBoards();
    BoardDTO updateBoard(String id, BoardDTO boardDTO);
    void deleteBoard(String id);
}