package org.project.community_nihon.service.utility.bookmark;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.project.community_nihon.domain.utility.Bookmark;
import org.project.community_nihon.dto.utility.BookmarkDTO;
import org.project.community_nihon.repository.utility.BookmarkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService{

    private final BookmarkRepository bookmarkRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public BookmarkDTO createBookmark(BookmarkDTO bookmarkDTO) {
        Bookmark bookmark = modelMapper.map(bookmarkDTO, Bookmark.class);
        Bookmark savedBookmark = bookmarkRepository.save(bookmark);
        return modelMapper.map(savedBookmark, BookmarkDTO.class);
    }

    @Transactional
    public List<BookmarkDTO> getAllBookmarks() {
        List<Bookmark> bookmarks = bookmarkRepository.findAll();
        return bookmarks.stream()
                .map(bookmark -> modelMapper.map(bookmark, BookmarkDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<BookmarkDTO> getBookmarkById(Long id) {
        Optional<Bookmark> bookmark = bookmarkRepository.findById(id);
        return bookmark.map(value -> modelMapper.map(value, BookmarkDTO.class));
    }

    @Transactional
    public void deleteBookmark(Long id) {
        bookmarkRepository.deleteById(id);
    }

}
