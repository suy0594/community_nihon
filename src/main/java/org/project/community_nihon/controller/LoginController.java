package org.project.community_nihon.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.project.community_nihon.dto.user.UserVODTO;
import org.project.community_nihon.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/user")
public class LoginController {

    private final UserService userService;

    @GetMapping("/join")
    public void joinGET() {

        log.info("join get...");

    }

    @GetMapping("/login")
    public void loginGET() {

        log.info("login get...");

    }

    @PostMapping("/join")
    public String joinPOST(UserVODTO userVODTO, RedirectAttributes
                           redirectAttributes) {

        log.info("join post...");
        log.info(userVODTO);

        try {
            userService.join(userVODTO);
        } catch (UserService.IdExistException e) {
            redirectAttributes.addFlashAttribute("error", "id");
            return "redirect:/user/join";
        }

        redirectAttributes.addFlashAttribute("result", "success");

        return "redirect:/user/login";

    }


}
