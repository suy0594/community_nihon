package org.project.community_nihon.controller.utility;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.project.community_nihon.dto.utility.CertificationDTO;
import org.project.community_nihon.service.utility.certification.CertificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/api/certifications")
@RequiredArgsConstructor
public class CertificationController {

    private final CertificationService certificationService;

    @PostMapping
    public ResponseEntity<CertificationDTO> createCertification(@RequestBody CertificationDTO certificationDTO) {
        CertificationDTO createdCertification = certificationService.createCertification(certificationDTO);
        return ResponseEntity.ok(createdCertification);
    }

    @GetMapping
    public ResponseEntity<List<CertificationDTO>> getAllCertifications() {
        List<CertificationDTO> certifications = certificationService.getAllCertifications();
        return ResponseEntity.ok(certifications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificationDTO> getCertificationById(@PathVariable Long id) {
        CertificationDTO certification = certificationService.getCertificationById(id).orElseThrow(() -> new RuntimeException("Certification not found"));
        return ResponseEntity.ok(certification);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CertificationDTO> updateCertification(@PathVariable Long id, @RequestBody CertificationDTO certificationDTO) {
        CertificationDTO updatedCertification = certificationService.updateCertification(id, certificationDTO);
        return ResponseEntity.ok(updatedCertification);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCertification(@PathVariable Long id) {
        certificationService.deleteCertification(id);
        return ResponseEntity.noContent().build();
    }
}