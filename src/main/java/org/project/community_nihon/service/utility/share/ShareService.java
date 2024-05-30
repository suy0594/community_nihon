package org.project.community_nihon.service.utility.share;

import org.project.community_nihon.dto.utility.ShareDTO;

import java.util.List;
import java.util.Optional;

public interface ShareService {

    public ShareDTO createShare(ShareDTO shareDTO);

    public List<ShareDTO> getAllShares();

    public Optional<ShareDTO> getShareById(Long id);

    public void deleteShare(Long id);

}
