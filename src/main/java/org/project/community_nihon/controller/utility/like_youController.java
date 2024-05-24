package org.project.community_nihon.controller.utility;

import lombok.RequiredArgsConstructor;
import org.project.community_nihon.dto.utility.like_youDTO;
import org.project.community_nihon.service.utility.like_you.like_youService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/like_yous")
@RequiredArgsConstructor
public class like_youController {

    private final like_youService likeYouService;

    @PostMapping
    public ResponseEntity<like_youDTO> createLikeYou(@RequestBody like_youDTO likeYouDTO) {
        like_youDTO createdLikeYou = likeYouService.createLikeYou(likeYouDTO);
        return ResponseEntity.ok(createdLikeYou);
    }

    @GetMapping
    public ResponseEntity<List<like_youDTO>> getAllLikeYous() {
        List<like_youDTO> likeYous = likeYouService.getAllLikeYous();
        return ResponseEntity.ok(likeYous);
    }

    @GetMapping("/{id}")
    public ResponseEntity<like_youDTO> getLikeYouById(@PathVariable Long id) {
        like_youDTO likeYou = likeYouService.getLikeYouById(id).orElseThrow(() -> new RuntimeException("LikeYou not found"));
        return ResponseEntity.ok(likeYou);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLikeYou(@PathVariable Long id) {
        likeYouService.deleteLikeYou(id);
        return ResponseEntity.noContent().build();
    }
}