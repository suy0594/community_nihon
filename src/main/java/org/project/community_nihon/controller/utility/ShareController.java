package org.project.community_nihon.controller.utility;

import lombok.RequiredArgsConstructor;
import org.project.community_nihon.dto.utility.ShareDTO;
import org.project.community_nihon.service.utility.share.ShareService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shares")
@RequiredArgsConstructor
public class ShareController {

    private final ShareService shareService;

    @PostMapping
    public ResponseEntity<ShareDTO> createShare(@RequestBody ShareDTO shareDTO) {
        ShareDTO createdShare = shareService.createShare(shareDTO);
        return ResponseEntity.ok(createdShare);
    }

    @GetMapping
    public ResponseEntity<List<ShareDTO>> getAllShares() {
        List<ShareDTO> shares = shareService.getAllShares();
        return ResponseEntity.ok(shares);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShareDTO> getShareById(@PathVariable Long id) {
        ShareDTO share = shareService.getShareById(id).orElseThrow(() -> new RuntimeException("Share not found"));
        return ResponseEntity.ok(share);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShare(@PathVariable Long id) {
        shareService.deleteShare(id);
        return ResponseEntity.noContent().build();
    }
}