package org.project.community_nihon.service.utility.certification;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.project.community_nihon.domain.utility.Certification;
import org.project.community_nihon.dto.utility.CertificationDTO;
import org.project.community_nihon.repository.utility.CertificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CertificationServiceImpl implements CertificationService{

    private final CertificationRepository certificationRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public CertificationDTO createCertification(CertificationDTO certificationDTO) {
        Certification certification = modelMapper.map(certificationDTO, Certification.class);
        Certification savedCertification = certificationRepository.save(certification);
        return modelMapper.map(savedCertification, CertificationDTO.class);
    }

    @Transactional(readOnly = true)
    public List<CertificationDTO> getAllCertifications() {
        List<Certification> certifications = certificationRepository.findAll();
        return certifications.stream()
                .map(certification -> modelMapper.map(certification, CertificationDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<CertificationDTO> getCertificationById(Long id) {
        Optional<Certification> certification = certificationRepository.findById(id);
        return certification.map(value -> modelMapper.map(value, CertificationDTO.class));
    }

    @Transactional
    public CertificationDTO updateCertification(Long id, CertificationDTO certificationDTO) {
        Certification certification = certificationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid certification Id:" + id));
        certification.setCertified(certificationDTO.getCertified());
        certification.setMaster(certificationDTO.getMaster());
        Certification updatedCertification = certificationRepository.save(certification);
        return modelMapper.map(updatedCertification, CertificationDTO.class);
    }

    @Transactional
    public void deleteCertification(Long id) {
        certificationRepository.deleteById(id);
    }
}
