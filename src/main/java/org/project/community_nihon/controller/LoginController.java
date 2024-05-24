package org.project.community_nihon.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.project.community_nihon.dto.user.UserVODTO;
import org.project.community_nihon.security.dto.UserSecurityDTO;
import org.project.community_nihon.service.user.UserService;
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

    private final UserService userService;

    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestParam String username, @RequestParam String password) {

        log.info("Login request received for ID: {}", username);

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("User successfully authenticated");

            return ResponseEntity.ok("User successfully authenticated");
        } catch (AuthenticationException e) {
            log.error("Authentication failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: " + e.getMessage());
        }
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
