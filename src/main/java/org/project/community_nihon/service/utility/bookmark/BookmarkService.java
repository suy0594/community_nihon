package org.project.community_nihon.service.utility.bookmark;

import org.project.community_nihon.domain.utility.Bookmark;
import org.project.community_nihon.dto.utility.BookmarkDTO;

import java.util.List;
import java.util.Optional;

public interface BookmarkService{

    public BookmarkDTO createBookmark(BookmarkDTO bookmarkDTO);

    public List<BookmarkDTO> getAllBookmarks();

    public Optional<BookmarkDTO> getBookmarkById(Long id);

    public void deleteBookmark(Long id);

}
