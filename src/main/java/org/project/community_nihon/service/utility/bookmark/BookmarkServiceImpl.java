package org.project.community_nihon.service.utility.bookmark;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.project.community_nihon.domain.user.UserVO;
import org.project.community_nihon.domain.utility.Bookmark;
import org.project.community_nihon.dto.utility.BookmarkDTO;
import org.project.community_nihon.repository.user.UserRepository;
import org.project.community_nihon.repository.utility.BookmarkRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService{

    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public BookmarkDTO createBookmark(BookmarkDTO bookmarkDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<UserVO> userVO = userRepository.findById(authentication.getName());

        Bookmark bookmark = modelMapper.map(bookmarkDTO, Bookmark.class);
        bookmark.setOrigin(userVO.get().getOrigin());
        Bookmark savedBookmark = bookmarkRepository.save(bookmark);
        return modelMapper.map(savedBookmark, BookmarkDTO.class);
    }

    // Account의 id를 기준으로 bookmark 엔티티 가져오기 (내 북마크 가져오기)
    @Transactional
    public List<Bookmark> getAllBookmarks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<UserVO> userVO = userRepository.findById(authentication.getName());

        List<Bookmark> bookmarks = bookmarkRepository.findByOrigin(userVO.get().getOrigin());

        return bookmarks;
    }

    // board id를 기준으로, 해당 게시글의 bookmark 전부 가져옴
    @Transactional
    public List<Bookmark> getBookmarkById(Long id) {
        List<Bookmark> bookmark = bookmarkRepository.findByBoardId(id);
        return bookmark;
    }

    @Transactional
    public void deleteBookmark(Long id) {
        bookmarkRepository.deleteById(id);
    }

}
