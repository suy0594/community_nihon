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
@RequestMapping("/home")
public class HomeController {
    private final BoardService boardService;


    @GetMapping
    public String board(Model model) {
        List<BoardDTO> board = boardService.getAllBoards();
        model.addAttribute("board", board);
        return "home"; // 뷰 이름 반환
    }

    @GetMapping("/create")
    public void createGet() {
        log.info("create....");
    }

    @PostMapping(value = "/create", consumes = "application/x-www-form-urlencoded")
    public String create(@RequestParam String content) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setContent(content);
        boardService.createBoard(boardDTO);
        return "redirect:/home";
    }

/*
    @GetMapping("/{id}")
    public String getBoard(@PathVariable String id) {
        List<BoardDTO> board = boardService.getBoard(id);

        return "boards/boardDetail";
    }
*/




}
