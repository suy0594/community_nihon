package org.project.community_nihon.service.utility.like_you;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.project.community_nihon.domain.utility.like_you;
import org.project.community_nihon.dto.utility.like_youDTO;
import org.project.community_nihon.repository.utility.LikeYouRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class like_youServiceImpl implements like_youService{

    private final LikeYouRepository likeYouRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public like_youDTO createLikeYou(like_youDTO likeYouDTO) {
        like_you likeYou = modelMapper.map(likeYouDTO, like_you.class);
        like_you savedLikeYou = likeYouRepository.save(likeYou);
        return modelMapper.map(savedLikeYou, like_youDTO.class);
    }

    @Transactional
    public List<like_youDTO> getAllLikeYous() {
        List<like_you> likeYous = likeYouRepository.findAll();
        return likeYous.stream()
                .map(likeYou -> modelMapper.map(likeYou, like_youDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<like_youDTO> getLikeYouById(Long id) {
        Optional<like_you> likeYou = likeYouRepository.findById(id);
        return likeYou.map(value -> modelMapper.map(value, like_youDTO.class));
    }

    @Transactional
    public void deleteLikeYou(Long id) {
        likeYouRepository.deleteById(id);
    }

}
