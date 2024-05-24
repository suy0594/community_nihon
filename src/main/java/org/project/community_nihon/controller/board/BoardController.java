package org.project.community_nihon.controller.board;

import lombok.RequiredArgsConstructor;
import org.project.community_nihon.dto.board.BoardDTO;
import org.project.community_nihon.service.board.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<Long> createBoard(@RequestBody BoardDTO boardDTO, @RequestParam String community) {
        Long createdBoardId = boardService.createBoard(boardDTO, community);
        return ResponseEntity.ok(createdBoardId);
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

    @PutMapping
    public ResponseEntity<Void> modifyBoard(@RequestBody BoardDTO boardDTO) {
        boardService.modifyBoard(boardDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }
}