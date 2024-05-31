package org.project.community_nihon.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.project.community_nihon.domain.board.Board;
import org.project.community_nihon.domain.user.UserProfile;
import org.project.community_nihon.service.board.BoardService;
import org.project.community_nihon.service.user.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping("/userProfile")
    public ResponseEntity<?> createUserProfile(
            @Validated @RequestParam("files") List<MultipartFile> files
    ) throws Exception {
        userProfileService.addUserProfile(UserProfile.builder()
                .build(), files);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/userProfile")
    public String getBoard(@RequestParam long id) {
        UserProfile userProfile = userProfileService.findBoard(id).orElseThrow(RuntimeException::new);
        String imgPath = userProfile.getStoredFileName();
        log.info(imgPath);
        return "<img src=" + imgPath + ">";
    }

}
