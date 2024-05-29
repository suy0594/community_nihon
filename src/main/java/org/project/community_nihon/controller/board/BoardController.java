package org.project.community_nihon.controller.board;

import lombok.RequiredArgsConstructor;
import org.project.community_nihon.domain.board.Board;
import org.project.community_nihon.domain.community.Community;
import org.project.community_nihon.dto.board.BoardDTO;
import org.project.community_nihon.service.board.BoardService;
import org.project.community_nihon.service.board.BoardServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardServiceImpl boardService;

    // 생성
    @PostMapping
    public ResponseEntity<BoardDTO> createBoard(String userId,
                                             String content,
                                              String title)
                                            {
        BoardDTO boardDTO = boardService.createBoard(userId, content, title);

        return ResponseEntity.ok(boardDTO);

    }

    @PutMapping
    public ResponseEntity<String> modifyBoard(Long boardId, String content) {
        String string = boardService.modifyBoard(boardId, content);
        return ResponseEntity.ok(string);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<BoardDTO>> getAllBoards() {
        List<BoardDTO> boards = boardService.getAllBoards();
        return ResponseEntity.ok(boards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardDTO> getBoardById(@PathVariable Long id) {
        BoardDTO board = boardService.getBoard(id);
        return ResponseEntity.ok(board);
    }
}