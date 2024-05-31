package org.project.community_nihon.service.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.domain.board.Board;
import org.project.community_nihon.domain.community.Community;
import org.project.community_nihon.domain.user.UserVO;
import org.project.community_nihon.domain.utility.Follow;
import org.project.community_nihon.dto.board.BoardDTO;
import org.project.community_nihon.repository.board.BoardRepository;
import org.modelmapper.ModelMapper;
import org.project.community_nihon.repository.community.CommunityRepository;
import org.project.community_nihon.repository.user.UserRepository;
import org.project.community_nihon.repository.utility.FollowRepository;
import org.project.community_nihon.repository.utility.LikeYouRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CommunityRepository communityRepository;
    private final FollowRepository followRepository;
    private final LikeYouRepository likeYouRepository;

    @Override
    public List<BoardDTO> getBoardsByUserId(String userId) {
        UserVO user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        List<Follow> follows1 = followRepository.FollowsByOrigin(user.getOrigin());   // 맞팔x 내가 팔로워
        List<Follow> follows2 = followRepository.FriendOriginsByFollow(user.getOrigin()); // 맞팔o 내가 팔로잉
        List<Follow> follows3 = followRepository.FriendFollowsByOrigin(user.getOrigin()); // 맞팔o 내가 팔로워

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd | HH:mm");

        List<Account> followingAccounts = new ArrayList<>();
        for (Follow follow : follows1) {
            followingAccounts.add(follow.getFollow());
        }
        for (Follow follow : follows3) {
            followingAccounts.add(follow.getFollow());
        }
        for (Follow follow : follows2) {
            followingAccounts.add(follow.getOrigin());
        }
        List<Account> distinctFollowingAccounts = followingAccounts.stream()
                .distinct()
                .collect(Collectors.toList());
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (Account account : distinctFollowingAccounts) {
            List<Board> boards = boardRepository.findByOrigin(account);
            for (Board board : boards) {
                BoardDTO boardDTO = new BoardDTO(); // BoardDTO 객체를 생성하고 필요한 정보를 설정
                boardDTO.setId(board.getId());
                boardDTO.setOrigin(board.getOrigin().getId());
                boardDTO.setContent(board.getContent());
                boardDTO.setPosterId(userRepository.getUserByAccount(board.getOrigin()));
                boardDTO.setUserId(userRepository.getUserByAccount(board.getOrigin()));
                boardDTO.setCreated_time(board.getCreated_time().format(formatter));
                boardDTO.setLikes(likeYouRepository.countlike_youByBoardId(board.getId()));
                boardDTOList.add(boardDTO);
            }
        }
        List<Board> boards = boardRepository.findByOrigin(user.getOrigin());
        for (Board board : boards) {
            BoardDTO boardDTO = new BoardDTO(); // BoardDTO 객체를 생성하고 필요한 정보를 설정
            boardDTO.setId(board.getId());
            boardDTO.setOrigin(board.getOrigin().getId());
            boardDTO.setContent(board.getContent());
            boardDTO.setUserId(userRepository.getUserByAccount(board.getOrigin()));
            boardDTO.setPosterId(userRepository.getUserByAccount(board.getOrigin()));
            boardDTO.setCreated_time(board.getCreated_time().format(formatter));
            boardDTO.setLikes(likeYouRepository.countlike_youByBoardId(board.getId()));
            boardDTOList.add(boardDTO);
        }
        List<BoardDTO> sortedBoardDTOs = boardDTOList.stream()
                .sorted(Comparator.comparing(BoardDTO::getCreated_time).reversed())
                .collect(Collectors.toList());

        return sortedBoardDTOs;

    }

    public BoardDTO convertToDTO(Board board) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd | HH:mm");
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(board.getId());
        boardDTO.setOrigin(board.getOrigin().getId());
        boardDTO.setContent(board.getContent());
        boardDTO.setPosterId(userRepository.getUserByAccount(board.getOrigin()));
        boardDTO.setUserId(userRepository.getUserByAccount(board.getOrigin()));
        boardDTO.setCreated_time(board.getCreated_time().format(formatter));
        return boardDTO;
    }

    @Override
    public List<BoardDTO> getAllBoards() {
        List<BoardDTO> boardDTOList = boardRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return boardDTOList;
    }


    @Override
    public BoardDTO createBoard(BoardDTO boardDTO) {
        Optional<UserVO> userVO = userRepository.findById(boardDTO.getUserId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd | HH:mm");

        Community group;
        if ((boardDTO.getTitle()) != null) {
            group = communityRepository.getCommunityByTitle(boardDTO.getUserId());
        }
        else {
            group = communityRepository.findById(boardDTO.getId()).get();
        }
        log.info("SDASDSAASDADSSDADSADSA: " + group);

        Board board = Board.builder()
                .origin(userVO.get().getOrigin())
                .community(group)
                .content(boardDTO.getContent())
                .build();
        Board result = boardRepository.save(board); // board 객체 저장


        BoardDTO boardDTO1 = new BoardDTO();
        boardDTO1.setUserId(userRepository.getUserByAccount(board.getOrigin()));
        boardDTO1.setContent(board.getContent());
        boardDTO1.setId(result.getId());
        boardDTO1.setPosterId(userRepository.getUserByAccount(board.getOrigin()));
        boardDTO1.setOrigin(board.getOrigin().getId());
        boardDTO1.setCreated_time(board.getCreated_time().format(formatter));
        boardDTO1.setLikes(0);
        boardDTO1.setTitle(group.getTitle());
        return boardDTO1;
    }

    @Override
    public BoardDTO modifyBoard(Long boardId, String content) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd | HH:mm");
        Optional<Board> board = boardRepository.findById(boardId);
        if(board.isPresent()) {
            board.get().updateContent(content);
            Board result = boardRepository.save(board.get());
            BoardDTO boardDTO = new BoardDTO();
            boardDTO.setUserId(userRepository.getUserByAccount(board.get().getOrigin()));
            boardDTO.setPosterId(userRepository.getUserByAccount(board.get().getOrigin()));
            boardDTO.setContent(board.get().getContent());
            boardDTO.setId(result.getId());
            boardDTO.setOrigin(board.get().getOrigin().getId());
            boardDTO.setCreated_time(board.get().getCreated_time().format(formatter));
            boardDTO.setLikes(boardDTO.getLikes());
            boardDTO.setTitle(board.get().getCommunity().getTitle());

            return boardDTO;
        } else {
            throw new IllegalArgumentException("No board with the given id found");
        }

    }

    @Transactional
    @Override
    public BoardDTO getBoardInfo(Long id) {   // userId, boardId

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd | HH:mm");
        Optional<Board> board = boardRepository.findById(id);
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setTitle(board.get().getCommunity().getTitle());
        boardDTO.setPosterId(userRepository.getUserByAccount(board.get().getOrigin()));
        boardDTO.setContent(board.get().getContent());
        boardDTO.setCreated_time(board.get().getCreated_time().format(formatter));

        return boardDTO;

    }

    @Override
    public void deleteBoard(Long id) {
        log.info("deleteeeeeeeeeeeee : " + id);

        Optional<Board> board = boardRepository.findById(id);
        if(board.isPresent()) {
            boardRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No board with the given id found");
        }
    }
}