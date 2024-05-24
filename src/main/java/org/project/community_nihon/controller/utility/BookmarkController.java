package org.project.community_nihon.controller.utility;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.project.community_nihon.dto.utility.BookmarkDTO;
import org.project.community_nihon.service.utility.bookmark.BookmarkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping
    public ResponseEntity<BookmarkDTO> createBookmark(@RequestBody BookmarkDTO bookmarkDTO) {
        BookmarkDTO createdBookmark = bookmarkService.createBookmark(bookmarkDTO);
        return ResponseEntity.ok(createdBookmark);
    }

    @GetMapping
    public ResponseEntity<List<BookmarkDTO>> getAllBookmarks() {
        List<BookmarkDTO> bookmarks = bookmarkService.getAllBookmarks();
        return ResponseEntity.ok(bookmarks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookmarkDTO> getBookmarkById(@PathVariable Long id) {
        BookmarkDTO bookmark = bookmarkService.getBookmarkById(id).orElseThrow(() -> new RuntimeException("Bookmark not found"));
        return ResponseEntity.ok(bookmark);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookmark(@PathVariable Long id) {
        bookmarkService.deleteBookmark(id);
        return ResponseEntity.noContent().build();
    }

}
