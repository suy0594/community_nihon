package org.project.community_nihon.service.utility.bookmark;

import org.project.community_nihon.domain.utility.Bookmark;
import org.project.community_nihon.dto.utility.BookmarkDTO;

import java.util.List;
import java.util.Optional;

public interface BookmarkService{

    BookmarkDTO createBookmark(BookmarkDTO bookmarkDTO);

    List<Bookmark> getAllBookmarks();

    List<Bookmark> getBookmarkById(Long id);

    void deleteBookmark(Long id);

}
