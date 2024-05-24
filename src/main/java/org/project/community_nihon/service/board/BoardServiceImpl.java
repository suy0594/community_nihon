package org.project.community_nihon.service.board;

import lombok.RequiredArgsConstructor;
import org.project.community_nihon.domain.board.Board;
import org.project.community_nihon.domain.community.Community;
import org.project.community_nihon.domain.user.UserVO;
import org.project.community_nihon.dto.board.BoardDTO;
import org.project.community_nihon.repository.board.BoardRepository;
import org.modelmapper.ModelMapper;
import org.project.community_nihon.repository.community.CommunityRepository;
import org.project.community_nihon.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CommunityRepository communityRepository;

    @Override
    public BoardDTO getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("Board not found"));
        return modelMapper.map(board, BoardDTO.class);
    }

    @Override
    public List<BoardDTO> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(board -> modelMapper.map(board, BoardDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Long createBoard(BoardDTO boardDTO, String community) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<UserVO> userVO = userRepository.findById(authentication.getName());

        Optional<Community> group = communityRepository.findById(community);

        Board board = modelMapper.map(boardDTO, Board.class);
        board.updateAccount(userVO.get().getOrigin()); // origin 값 설정
        board.setCommunity(group.get());

        boardRepository.save(board); // board 객체 저장
        return board.getId();
    }

    @Override
    public void modifyBoard(BoardDTO boardDTO) {
        Optional<Board> board = boardRepository.findById(boardDTO.getId());
        if(board.isPresent()) {
            board.get().updateContent(boardDTO.getContent());
            board.get().updateAccount(boardDTO.getOrigin());
            boardRepository.save(board.get());
        } else {
            throw new IllegalArgumentException("No board with the given id found");
        }
    }

    @Override
    public void deleteBoard(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        if(board.isPresent()) {
            boardRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No board with the given id found");
        }
    }
}