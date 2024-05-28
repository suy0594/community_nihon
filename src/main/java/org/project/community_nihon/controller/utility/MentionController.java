package org.project.community_nihon.controller.utility;

import lombok.RequiredArgsConstructor;
import org.project.community_nihon.dto.utility.MentionDTO;
import org.project.community_nihon.service.utility.mention.MentionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mentions")
@RequiredArgsConstructor
public class MentionController {

    private final MentionService mentionService;

    @PostMapping
    public ResponseEntity<MentionDTO> createMention(@RequestBody MentionDTO mentionDTO) {
        MentionDTO createdMention = mentionService.createMention(mentionDTO);
        return ResponseEntity.ok(createdMention);
    }

    @GetMapping
    public ResponseEntity<List<MentionDTO>> getAllMentions() {
        List<MentionDTO> mentions = mentionService.getAllMentions();
        return ResponseEntity.ok(mentions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MentionDTO> getMentionById(@PathVariable Long id) {
        MentionDTO mention = mentionService.getMentionById(id).orElseThrow(() -> new RuntimeException("Mention not found"));
        return ResponseEntity.ok(mention);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMention(@PathVariable Long id) {
        mentionService.deleteMention(id);
        return ResponseEntity.noContent().build();
    }
}