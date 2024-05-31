package org.project.community_nihon.service.user;

import lombok.RequiredArgsConstructor;
import org.project.community_nihon.domain.board.Board;
import org.project.community_nihon.domain.user.UserProfile;
import org.project.community_nihon.repository.board.BoardRepository;
import org.project.community_nihon.repository.user.UserProfileRepository;
import org.project.community_nihon.service.user.handler.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    private final FileHandler fileHandler;

    public UserProfile addUserProfile(
            UserProfile userProfile,
            List<MultipartFile> files
    ) throws Exception {
        // 파일을 저장하고 그 Board 에 대한 list 를 가지고 있는다
        List<UserProfile> list = fileHandler.parseFileInfo(userProfile.getId(), files);

        if (list.isEmpty()){
            //;
        }
        // 파일에 대해 DB에 저장하고 가지고 있을 것
        else{
            List<UserProfile> pictureBeans = new ArrayList<>();
            for (UserProfile userProfile1 : list) {
                pictureBeans.add(userProfileRepository.save(userProfile1));
            }
        }

        return userProfileRepository.save(userProfile);
    }

    public List<UserProfile> findBoards() {
        return userProfileRepository.findAll();
    }

    public Optional<UserProfile> findBoard(Long id) {
        return userProfileRepository.findById(id);
    }

}