package org.project.community_nihon.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.project.community_nihon.dto.UserJoinDTO;
import org.project.community_nihon.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping("/join")
    public String joinPOST(UserJoinDTO userJoinDTO, RedirectAttributes
                           redirectAttributes) {

        log.info("join post...");
        log.info(userJoinDTO);

        try {
            userService.join(userJoinDTO);
        } catch (UserService.IdExistException e) {
            redirectAttributes.addFlashAttribute("error", "id");
            return "redirect:/user/join";
        }

        redirectAttributes.addFlashAttribute("result", "success");

        return "redirect:/user/login";

    }


}
