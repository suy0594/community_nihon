package org.project.community_nihon.controller.board;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.project.community_nihon.dto.board.ReplyBoardDTO;
import org.project.community_nihon.service.board.ReplyServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/replies")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(origins = "*")
public class ReplyController {

    private final ReplyServiceImpl replyService;

    @PostMapping("/create")
    public ResponseEntity<ReplyBoardDTO> createReply(ReplyBoardDTO replyBoardDTO) {
        ReplyBoardDTO replyBoardDTO1 = replyService.createReply(replyBoardDTO);
        return ResponseEntity.ok(replyBoardDTO1);
    }


}
