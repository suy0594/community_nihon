package org.project.community_nihon.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.domain.community.Community;
import org.project.community_nihon.dto.community.CommunityDTO;
import org.project.community_nihon.service.community.CommunityServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/communities")
@RequiredArgsConstructor
@Log4j2
public class CommunityController {

    private final CommunityServiceImpl communityService;

    // 그룸 이름, 포스트 수, 멤버 수, 설명, ID;
    @GetMapping
    public ResponseEntity<List<CommunityDTO>> getAllCommunities() {
        List<CommunityDTO> communityDTOS = communityService.getAllCommunities();
        return ResponseEntity.ok(communityDTOS);
    }


    @PostMapping("/create")
    public ResponseEntity<CommunityDTO> createCommunity(@RequestBody CommunityDTO communityDTO) {
        CommunityDTO communityDTO1 = communityService.createCommunity(communityDTO);
        log.info(communityDTO1);

        return ResponseEntity.ok(communityDTO1);
    }
}