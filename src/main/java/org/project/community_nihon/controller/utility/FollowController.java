package org.project.community_nihon.controller.utility;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.project.community_nihon.dto.utility.FollowDTO;
import org.project.community_nihon.service.utility.follow.FollowService;
import org.project.community_nihon.service.utility.follow.FollowServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/api/follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowServiceImpl followService;

    @PostMapping
    public ResponseEntity<FollowDTO> createFollow(@RequestBody FollowDTO followDTO) {
        log.info("post: " + followDTO);

        FollowDTO createdFollow = followService.createFollow(followDTO);
        log.info(createdFollow);
        return ResponseEntity.ok(createdFollow);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FollowDTO> getFollow(@PathVariable Long id) {
        FollowDTO follow = followService.getFollow(id);
        return ResponseEntity.ok(follow);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FollowDTO> updateFollow(@PathVariable Long id, @RequestBody FollowDTO followDTO) {
        FollowDTO updatedFollow = followService.updateFollow(id, followDTO);
        return ResponseEntity.ok(updatedFollow);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFollow(@PathVariable Long id) {
        followService.deleteFollow(id);
        return ResponseEntity.noContent().build();
    }
}