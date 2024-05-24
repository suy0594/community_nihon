package org.project.community_nihon.service.board;

import lombok.RequiredArgsConstructor;
import org.project.community_nihon.domain.board.Board;
import org.project.community_nihon.domain.user.UserVO;
import org.project.community_nihon.dto.board.BoardDTO;
import org.project.community_nihon.repository.board.BoardRepository;
import org.modelmapper.ModelMapper;
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

    @Override
    public void createBoard(BoardDTO boardDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<UserVO> userVO = userRepository.findById(authentication.getName());
        Board board = new Board(); // 새로운 Board 엔티티 객체 생성
        board.updateContent(boardDTO.getContent()); // content 값 설정
        board.updateAccount(userVO.get().getOrigin()); // origin 값 설정

        boardRepository.save(board); // board 객체 저장
        return;
    }

    @Override
    public BoardDTO getBoard(String id) {
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
    public BoardDTO updateBoard(String id, BoardDTO boardDTO) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("Board not found"));
        board.updateContent(boardDTO.getContent());
        board = boardRepository.save(board);
        return modelMapper.map(board, BoardDTO.class);
    }

    @Override
    public void deleteBoard(String id) {
        boardRepository.deleteById(id);
    }
}