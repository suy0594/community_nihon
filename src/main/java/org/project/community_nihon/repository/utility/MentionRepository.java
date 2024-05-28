package org.project.community_nihon.repository.utility;

import org.project.community_nihon.domain.utility.Mention;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentionRepository extends JpaRepository<Mention, Long> {
}
