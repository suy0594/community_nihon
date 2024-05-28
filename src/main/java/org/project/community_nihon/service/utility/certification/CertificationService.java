package org.project.community_nihon.service.utility.certification;

import org.project.community_nihon.domain.utility.Certification;
import org.project.community_nihon.dto.utility.CertificationDTO;

import java.util.List;
import java.util.Optional;

public interface CertificationService {

    //public CertificationDTO createCertification(CertificationDTO certificationDTO);

    public List<Certification> getAllCertifications(Long id);

    List<Certification> getCertificationById(String id);

    CertificationDTO updateCertification(String id, CertificationDTO certificationDTO);

    void deleteCertification(String id, CertificationDTO certificationDTO);


}
