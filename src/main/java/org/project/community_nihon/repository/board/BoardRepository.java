package org.project.community_nihon.repository.board;

import org.project.community_nihon.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
