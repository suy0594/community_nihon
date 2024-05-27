package org.project.community_nihon.service.utility.like_you;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.project.community_nihon.domain.board.Board;
import org.project.community_nihon.domain.user.UserVO;
import org.project.community_nihon.domain.utility.like_you;
import org.project.community_nihon.dto.utility.like_youDTO;
import org.project.community_nihon.repository.user.UserRepository;
import org.project.community_nihon.repository.utility.LikeYouRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class like_youServiceImpl implements like_youService{

    private final LikeYouRepository likeYouRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public like_youDTO createLikeYou(like_youDTO likeYouDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<UserVO> userVO = userRepository.findById(authentication.getName());

        like_you likeYou = modelMapper.map(likeYouDTO, like_you.class);

        likeYou.setOrigin(userVO.get().getOrigin());

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
    public List<like_you> getLikeYouByBoardId(Long id) {
        List<like_you> likeYou = likeYouRepository.findByBoardId(id);
        return likeYou;
    }


    // board id
    @Transactional
    public void deleteLikeYou(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<UserVO> userVO = userRepository.findById(authentication.getName());

        Long origin_id = userVO.get().getOrigin().getId();
        Optional<like_you> likeYou = likeYouRepository.findByBoardIdAndOriginId(id, origin_id);
        likeYou.ifPresent(likeYouRepository::delete);
    }

}
