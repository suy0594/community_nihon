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
@CrossOrigin(origins = "*")
public class BoardController {

    private final BoardServiceImpl boardService;

    // 생성
    @PostMapping("/create")
    public ResponseEntity<BoardDTO> createBoard(@RequestBody BoardDTO boardDTO) {
        BoardDTO boardDTO1 = boardService.createBoard(boardDTO);

        return ResponseEntity.ok(boardDTO);

    }

    @PutMapping
    public ResponseEntity<String> modifyBoard(Long boardId, String content) {
        String string = boardService.modifyBoard(boardId, content);
        return ResponseEntity.ok(string);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoarsatd(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<BoardDTO>> getAllBoards() {
        List<BoardDTO> boards = boardService.getAllBoards();
        return ResponseEntity.ok(boards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<BoardDTO>> getBoardById(@PathVariable String id) {
        List<BoardDTO> board = boardService.getBoardsByUserId(id);
        return ResponseEntity.ok(board);
    }
}