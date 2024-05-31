package org.project.community_nihon.controller.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class BoardController {

    private final BoardServiceImpl boardService;

    // 생성
    @PostMapping("/create")
    public ResponseEntity<BoardDTO> createBoard(@RequestBody BoardDTO boardDTO) {

        log.info(boardDTO);

        BoardDTO boardDTO1 = boardService.createBoard(boardDTO);

        return ResponseEntity.ok(boardDTO1);

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


    @GetMapping("/{id}")    // 팔로우중인 사람 게시글   // userId
    public ResponseEntity<List<BoardDTO>> getBoardById(@PathVariable String id) {
        List<BoardDTO> board = boardService.getBoardsByUserId(id);
        log.info(board);
        return ResponseEntity.ok(board);
    }

    @GetMapping("/{id1}/{id2}")
    public ResponseEntity<BoardDTO> getBoardInfo(@PathVariable String id1,
                                                 @PathVariable Long id2) {
        BoardDTO boardDTO = boardService.getBoardInfo(id2);
        return ResponseEntity.ok(boardDTO);
    }
}