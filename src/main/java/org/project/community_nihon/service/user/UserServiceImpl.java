package org.project.community_nihon.service.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.domain.board.Board;
import org.project.community_nihon.domain.community.Community;
import org.project.community_nihon.domain.user.UserRole;
import org.project.community_nihon.domain.user.UserVO;
import org.project.community_nihon.domain.utility.Certification;
import org.project.community_nihon.dto.board.BoardDTO;
import org.project.community_nihon.dto.community.CommunityDTO;
import org.project.community_nihon.dto.user.UserVODTO;
import org.project.community_nihon.dto.utility.FollowDTO;
import org.project.community_nihon.repository.account.AccountRepository;
import org.project.community_nihon.repository.board.BoardRepository;
import org.project.community_nihon.repository.community.CommunityRepository;
import org.project.community_nihon.repository.user.UserRepository;
import org.project.community_nihon.repository.utility.CertificationRepository;
import org.project.community_nihon.repository.utility.FollowRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    private final BoardRepository boardRepository;

    private final CommunityRepository communityRepository;

    private final CertificationRepository certificationRepository;

    private final FollowRepository followRepository;

    @Transactional
    @Override
    public UserVODTO registerUser(String id) {

        Optional<UserVO> userVO = userRepository.findById(id);

        UserVODTO userVODTO = new UserVODTO();
        userVODTO.setId(id);
        userVODTO.setPhone(userVO.get().getPhone());
        userVODTO.setEmail(userVO.get().getEmail());
        userVODTO.setScreen_name(userVO.get().getScreen_name());
        userVODTO.setFollowing_count(followRepository.countFollowsByOrigin(userVO.get().getOrigin()) +
                followRepository.countFriendOriginsByFollow(userVO.get().getOrigin()));
        userVODTO.setFollower_count(followRepository.countOriginsByFollow(userVO.get().getOrigin()) +
                followRepository.countFriendFollowsByOrigin(userVO.get().getOrigin()));

        userVODTO.setNumber_of_posts(boardRepository.countPostsByAccountId(userVO.get().getOrigin().getId()));

        return userVODTO;
    }

    @Transactional
    @Override
    public List<BoardDTO> registerUser2(String id) {
        UserVO user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        List<Board> boards = boardRepository.findByOrigin(user.getOrigin());
        return boards.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BoardDTO convertToDTO(Board board) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd | HH:mm");
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(board.getId());
        boardDTO.setOrigin(board.getOrigin().getId());
        boardDTO.setContent(board.getContent());
        boardDTO.setUserId(userRepository.getUserByAccount(board.getOrigin()));
        boardDTO.setPosterId(userRepository.getUserByAccount(board.getOrigin()));
        boardDTO.setCreated_time(board.getCreated_time().format(formatter));
        return boardDTO;
    }

    @Transactional
    @Override
    public String login(String userId, String password) {
        Optional<UserVO> userVOOptional = userRepository.findById(userId);
        if (userVOOptional.isPresent()) {
            UserVO user = userVOOptional.get();
            if (user.getPassword().equals(password)) {
                return "success";
            }
        }
        return "fail";
    }

    @Override
    public void join(UserVODTO userVODTO) throws IdExistException {

        String id = userVODTO.getId();

        boolean exist = userRepository.existsById(id);

        if (exist) {
            throw new IdExistException();
        }

        Account account = Account.builder().build();
        Account result = accountRepository.save(account);

        UserVO userVO = modelMapper.map(userVODTO, UserVO.class);
        userVO.addRole(UserRole.USER);
        userVO.addAccount(result);


        log.info("===========");
        log.info(userVO);
        log.info(userVO.getRoleSet());

        userRepository.save(userVO);

        Community community = Community.builder()
                .title(userVO.getId())
                .origin_member(userVO.getOrigin())
                .is_group(false)
                .build();

        communityRepository.save(community);

        Certification certification = Certification.builder()
                .master(userVO.getOrigin())
                .community(community)
                .build();

        certificationRepository.save(certification);

    }
}
