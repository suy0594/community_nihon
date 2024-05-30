package org.project.community_nihon.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.project.community_nihon.dto.user.UserVODTO;
import org.project.community_nihon.repository.user.UserRepository;
import org.project.community_nihon.service.user.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(origins = "*")
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/{id}")
    public ResponseEntity<UserVODTO> registerUser(@PathVariable String id) {
        return ResponseEntity.ok(userService.registerUser(id));
    }

}
