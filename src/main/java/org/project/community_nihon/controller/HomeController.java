package org.project.community_nihon.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.project.community_nihon.dto.board.BoardDTO;
import org.project.community_nihon.service.board.BoardService;
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

    private final BoardService boardService;


    @GetMapping("/home")
    public List<BoardDTO> board() {
        List<BoardDTO> board = boardService.getAllBoards();

        return board;
    }

/*
    @GetMapping("/{id}")
    public String getBoard(@PathVariable String id) {
        List<BoardDTO> board = boardService.getBoard(id);

        return "boards/boardDetail";
    }
*/




}
