package org.project.community_nihon.service.utility.mention;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.project.community_nihon.domain.utility.Mention;
import org.project.community_nihon.dto.utility.MentionDTO;
import org.project.community_nihon.repository.utility.MentionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MentionServiceImpl implements MentionService{

    private final MentionRepository mentionRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public MentionDTO createMention(MentionDTO mentionDTO) {
        Mention mention = modelMapper.map(mentionDTO, Mention.class);
        Mention savedMention = mentionRepository.save(mention);
        return modelMapper.map(savedMention, MentionDTO.class);
    }

    @Transactional
    public List<MentionDTO> getAllMentions() {
        List<Mention> mentions = mentionRepository.findAll();
        return mentions.stream()
                .map(mention -> modelMapper.map(mention, MentionDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<MentionDTO> getMentionById(Long id) {
        Optional<Mention> mention = mentionRepository.findById(id);
        return mention.map(value -> modelMapper.map(value, MentionDTO.class));
    }

    @Transactional
    public void deleteMention(Long id) {
        mentionRepository.deleteById(id);
    }


}
