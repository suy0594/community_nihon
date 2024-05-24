package org.project.community_nihon.repository.utility;

import org.project.community_nihon.domain.utility.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}
