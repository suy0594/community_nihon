package org.project.community_nihon.controller;

import lombok.RequiredArgsConstructor;
import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.domain.community.Community;
import org.project.community_nihon.dto.community.CommunityDTO;
import org.project.community_nihon.service.community.CommunityServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/communities")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityServiceImpl communityService;

    @PostMapping("/create")
    public ResponseEntity<Community> createCommunity(@RequestBody CommunityDTO communityDTO) {
        Community community = communityService.createCommunity(communityDTO);
        return ResponseEntity.ok(community);
    }
}