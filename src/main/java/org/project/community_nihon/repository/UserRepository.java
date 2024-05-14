package org.project.community_nihon.repository;

import org.project.community_nihon.domain.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<UserVO, Long> {
}
