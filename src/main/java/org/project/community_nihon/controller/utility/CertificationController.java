package org.project.community_nihon.controller.utility;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.project.community_nihon.domain.utility.Certification;
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

    /*
    @PostMapping
    public ResponseEntity<CertificationDTO> createCertification(@RequestBody CertificationDTO certificationDTO) {
        CertificationDTO createdCertification = certificationService.createCertification(certificationDTO);

        return ResponseEntity.ok(createdCertification);
    }
     */

    // community의 id를 받으면 관리자 list를 return
    @GetMapping
    public ResponseEntity<List<Certification>> getAllCertifications(Long id) {
        List<Certification> certification = certificationService.getAllCertifications(id);
        return ResponseEntity.ok(certification);
    }

    // Account id를 기준으로 id가 관리자인 community 리스트 리턴
    @GetMapping("/{id}")
    public ResponseEntity<List<Certification>> getCertificationById(@PathVariable String id) {
        List<Certification> certifications = certificationService.getCertificationById(id);
        return ResponseEntity.ok(certifications);
    }

    // user id 받아와서, service에서 account id 찾고~
    @PutMapping("/{id}")
    public ResponseEntity<CertificationDTO> updateCertification(@PathVariable String id  /* user */,
                                                                @RequestBody CertificationDTO certificationDTO) {
        CertificationDTO updatedCertification = certificationService.updateCertification(id, certificationDTO);
        return ResponseEntity.ok(updatedCertification);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCertification(@PathVariable String id  /* user */,
                                                    @RequestBody CertificationDTO certificationDTO) {
        certificationService.deleteCertification(id, certificationDTO);
        return ResponseEntity.noContent().build();
    }
}