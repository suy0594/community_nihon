package org.project.community_nihon.service.utility.certification;

import org.project.community_nihon.dto.utility.CertificationDTO;

import java.util.List;
import java.util.Optional;

public interface CertificationService {

    public CertificationDTO createCertification(CertificationDTO certificationDTO);

    public List<CertificationDTO> getAllCertifications();

    public Optional<CertificationDTO> getCertificationById(Long id);

    public CertificationDTO updateCertification(Long id, CertificationDTO certificationDTO);

    public void deleteCertification(Long id);


}
