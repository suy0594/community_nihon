package org.project.community_nihon.service.utility.mention;

import org.project.community_nihon.dto.utility.MentionDTO;

import java.util.List;
import java.util.Optional;

public interface MentionService {

    public MentionDTO createMention(MentionDTO mentionDTO);

    public List<MentionDTO> getAllMentions();

    public Optional<MentionDTO> getMentionById(Long id);

    public void deleteMention(Long id);


}
