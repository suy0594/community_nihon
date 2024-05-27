package org.project.community_nihon.service.utility.certification;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.domain.user.UserVO;
import org.project.community_nihon.domain.utility.Certification;
import org.project.community_nihon.dto.utility.CertificationDTO;
import org.project.community_nihon.repository.user.UserRepository;
import org.project.community_nihon.repository.utility.CertificationRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CertificationServiceImpl implements CertificationService{

    private final CertificationRepository certificationRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    /*
    // 나중에 고치기
    @Transactional
    public CertificationDTO createCertification(CertificationDTO certificationDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<UserVO> userVO = userRepository.findById(authentication.getName());

        Certification certification = Certification.builder()
                .community(certificationDTO.getCommunity())
                .master(userVO.get().getOrigin())
                .build();

        Certification savedCertification = certificationRepository.save(certification);
        return modelMapper.map(savedCertification, CertificationDTO.class);
    }
    */

    // community의 id를 받으면 관리자 List를 리턴
    @Transactional
    public List<Certification> getAllCertifications(Long id) {
        List<Certification> certifications = certificationRepository.findByCommunityId(id);
        return certifications;
    }

    // Account id를 기준으로 id가 관리자인 community 리스트 리턴
    @Transactional
    public List<Certification> getCertificationById(String id) {

        Account account = userRepository.findAccountByUserId(id);

        List<Certification> certification = certificationRepository.findByMasterId(account.getId());
        return certification;
    }

    // Account id 받아와서 certification 등록
    @Transactional
    public CertificationDTO updateCertification(String id, CertificationDTO certificationDTO) {

        Account account = userRepository.findAccountByUserId(id);
        certificationDTO.setMaster(account);

        Certification certification = modelMapper.map(certificationDTO, Certification.class);
        Certification updatedCertification = certificationRepository.save(certification);
        return modelMapper.map(updatedCertification, CertificationDTO.class);
    }


    @Transactional
    public void deleteCertification(String id, CertificationDTO certificationDTO) {

        Account account = userRepository.findAccountByUserId(id);
        certificationDTO.setMaster(account);

        certificationRepository.deleteByCommunityAndMaster(certificationDTO.getCommunity(),
                                                            certificationDTO.getMaster());
    }
}
