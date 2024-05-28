package org.project.community_nihon.service.board;

import org.project.community_nihon.dto.board.BoardDTO;

import java.util.List;

public interface BoardService {
    Long createBoard(BoardDTO boardDTO, Long community);
    BoardDTO getBoard(Long id);
    List<BoardDTO> getAllBoards();
    void modifyBoard(BoardDTO boardDTO);
    void deleteBoard(Long id);
}