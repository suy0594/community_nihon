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
        List<BoardDTO> boardDTOList = boardRepository.findAll().stream()
                .map(board -> {
                    BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
                    String userId = userRepository.getUserByAccount(board.getOrigin());
                    boardDTO.setUserId(userId);
                    return boardDTO;
                })
                .collect(Collectors.toList());

        return boardDTOList;
    }

    @Override
    public BoardDTO createBoard(String userId,
                                String content,
                                String title) {
        Optional<UserVO> userVO = userRepository.findById(userId);

        Community group = communityRepository.getCommunityByTitle(title);

        Board board = Board.builder()
                        .origin(userVO.get().getOrigin())
                                .community(group)
                                        .content(content)
                                                .build();
        Board result = boardRepository.save(board); // board 객체 저장


        BoardDTO boardDTO1 = modelMapper.map(result, BoardDTO.class);
        boardDTO1.setUserId(userId);

        return boardDTO1;
    }

    @Override
    public String modifyBoard(Long BoardId,String content) {
        Optional<Board> board = boardRepository.findById(BoardId);
        if(board.isPresent()) {
            board.get().updateContent(content);
            boardRepository.save(board.get());
            return "success";
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