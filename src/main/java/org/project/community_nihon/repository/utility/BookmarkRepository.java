package org.project.community_nihon.repository.utility;

import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.domain.utility.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    @Query("SELECT b FROM Bookmark b WHERE b.origin = :origin")
    List<Bookmark> findByOrigin(@Param("origin") Account origin);

    @Query("SELECT b FROM Bookmark b JOIN b.board brd WHERE brd.id = :id")
    List<Bookmark> findByBoardId(@Param("id") Long id);

}
