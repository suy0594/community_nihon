package org.project.community_nihon.service.board;

import lombok.RequiredArgsConstructor;
import org.project.community_nihon.domain.board.Board;
import org.project.community_nihon.domain.board.ReplyBoard;
import org.project.community_nihon.dto.board.ReplyBoardDTO;
import org.project.community_nihon.repository.account.AccountRepository;
import org.project.community_nihon.repository.board.BoardRepository;
import org.project.community_nihon.repository.board.ReplyBoardRepository;
import org.project.community_nihon.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyBoardRepository replyBoardRepository;

    private final BoardRepository boardRepository;

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public ReplyBoardDTO createReply(ReplyBoardDTO replyBoardDTO) {
        ReplyBoard replyBoard = ReplyBoard.builder()
                .board(boardRepository.findById(replyBoardDTO.getPostId()).get())
                .content(replyBoardDTO.getContent())
                .origin(userRepository.findAccountByUserId(replyBoardDTO.getUserId()))
                .build();
        ReplyBoard result = replyBoardRepository.save(replyBoard);

        ReplyBoardDTO replyBoardDTO1 = new ReplyBoardDTO();
        replyBoardDTO1.setId(result.getId());
        replyBoardDTO1.setUserId(userRepository.getUserByAccount(result.getOrigin()));
        replyBoardDTO1.setBoardId(result.getBoard().getId());
        replyBoardDTO1.setPostId(result.getBoard().getId());
        replyBoardDTO1.setContent(result.getContent());

        return replyBoardDTO1;

    }


    @Transactional
    @Override
    public List<ReplyBoardDTO> getReplyByBoardId(Long postId) {
        List<ReplyBoard> replyBoards = replyBoardRepository.getReplyBoardsByBoard_Id(postId);

        List<ReplyBoardDTO> replyBoardDTOs = replyBoards.stream()
                .map(replyBoard -> {
                    ReplyBoardDTO dto = new ReplyBoardDTO();
                    dto.setId(replyBoard.getId());
                    dto.setContent(replyBoard.getContent());
                    dto.setBoardId(replyBoard.getBoard().getId());
                    dto.setOrigin(replyBoard.getOrigin().getId());
                    dto.setUserId(userRepository.getUserByAccount(replyBoard.getOrigin()));
                    dto.setPostId(replyBoard.getBoard().getId());
                    return dto;
                })
                .collect(Collectors.toList());

        return replyBoardDTOs;

    }


}
