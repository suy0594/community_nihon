package org.project.community_nihon.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.project.community_nihon.dto.board.BoardDTO;
import org.project.community_nihon.dto.user.UserVODTO;
import org.project.community_nihon.repository.user.UserRepository;
import org.project.community_nihon.service.user.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(origins = "*")
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserVODTO> registerUser(@PathVariable String id) {
        return ResponseEntity.ok(userService.registerUser(id));
    }

    @GetMapping("/{id1}/profile/{id2}")
    public ResponseEntity<List<BoardDTO>> registerUser2(@PathVariable String id1,
                                                        @PathVariable String id2) {

        log.info("==============================: " + id1 + "=================" + id2);

        List<BoardDTO> boardDTOList = userService.registerUser2(id2);
        log.info(boardDTOList);
        return ResponseEntity.ok(boardDTOList);
    }

}
