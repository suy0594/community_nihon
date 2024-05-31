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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyBoardRepository replyBoardRepository;

    private final BoardRepository boardRepository;

    private final UserRepository userRepository;

    @Override
    public ReplyBoardDTO createReply(ReplyBoardDTO replyBoardDTO) {
        ReplyBoard replyBoard = ReplyBoard.builder()
                .board(boardRepository.findById(replyBoardDTO.getBoardId()).get())
                .content(replyBoardDTO.getContent())
                .origin(userRepository.findAccountByUserId(replyBoardDTO.getUserId()))
                .build();
        ReplyBoard result = replyBoardRepository.save(replyBoard);

        ReplyBoardDTO replyBoardDTO1 = new ReplyBoardDTO();
        replyBoardDTO1.setId(result.getId());
        replyBoardDTO1.setUserId(userRepository.getUserByAccount(result.getOrigin()));
        replyBoardDTO1.setBoardId(result.getBoard().getId());
        replyBoardDTO1.setContent(result.getContent());

        return replyBoardDTO1;

    }


    @Override
    public ReplyBoardDTO getReplyByBoardId(ReplyBoardDTO replyBoardDTO) {
        Optional<Board> board = boardRepository.findById(replyBoardDTO.getBoardId());

        ReplyBoardDTO replyBoardDTO1 = new ReplyBoardDTO();
        return replyBoardDTO1;
    }


}
