package org.project.community_nihon.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.project.community_nihon.dto.user.UserVODTO;
import org.project.community_nihon.security.dto.UserLogin;
import org.project.community_nihon.security.dto.UserSecurityDTO;
import org.project.community_nihon.service.user.UserService;
import org.project.community_nihon.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LoginController {

    private final UserServiceImpl userService;

    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserLogin userLogin) {

        log.info(userLogin);

        String string = userService.login(userLogin.getUserId(), userLogin.getPassword());

        return ResponseEntity.ok(string);
    }



    @GetMapping("/join")
    public void joinGET() {

        log.info("join get...");

    }

    @GetMapping("/login")
    public void loginGET() {

        log.info("login get...");

    }

    @PostMapping("/join")
    public ResponseEntity<?> joinPOST(@RequestBody UserVODTO userVODTO) {

        log.info("join post...");
        log.info(userVODTO.toString());

        try {
            userService.join(userVODTO);
        } catch (UserService.IdExistException e) {
            return new ResponseEntity<>("ID already exists", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("User successfully registered", HttpStatus.CREATED);
    }


}
