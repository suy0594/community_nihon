package org.project.community_nihon.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.project.community_nihon.dto.board.BoardDTO;
import org.project.community_nihon.service.board.BoardService;
import org.project.community_nihon.service.board.BoardServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Log4j2
@RequestMapping("/api")
public class HomeController {

    private final BoardServiceImpl boardService;


    // 홈 화면 (내가 팔로우 중인거)  return할 때, 게시글 id 포함하기
    @GetMapping("/home")
    public ResponseEntity<List<BoardDTO>> board() {
        List<BoardDTO> board = boardService.getAllBoards();

        return ResponseEntity.ok(board);
    }

    // userId의 게시글들, return할 때, 게시글 id 포함하기



/*
    @GetMapping("/{id}")
    public String getBoard(@PathVariable String id) {
        List<BoardDTO> board = boardService.getBoard(id);

        return "boards/boardDetail";
    }
*/




}
