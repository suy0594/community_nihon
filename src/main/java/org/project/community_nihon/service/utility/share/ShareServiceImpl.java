package org.project.community_nihon.service.utility.share;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.project.community_nihon.domain.utility.Share;
import org.project.community_nihon.dto.utility.ShareDTO;
import org.project.community_nihon.repository.utility.ShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShareServiceImpl implements ShareService{

    private final ShareRepository shareRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public ShareDTO createShare(ShareDTO shareDTO) {
        Share share = modelMapper.map(shareDTO, Share.class);
        Share savedShare = shareRepository.save(share);
        return modelMapper.map(savedShare, ShareDTO.class);
    }

    @Transactional
    public List<ShareDTO> getAllShares() {
        List<Share> shares = shareRepository.findAll();
        return shares.stream()
                .map(share -> modelMapper.map(share, ShareDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<ShareDTO> getShareById(Long id) {
        Optional<Share> share = shareRepository.findById(id);
        return share.map(value -> modelMapper.map(value, ShareDTO.class));
    }

    @Transactional
    public void deleteShare(Long id) {
        shareRepository.deleteById(id);
    }


}
