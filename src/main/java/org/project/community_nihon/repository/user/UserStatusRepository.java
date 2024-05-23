package org.project.community_nihon.repository.user;

import org.project.community_nihon.domain.user.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStatusRepository extends JpaRepository<UserStatus, String> {
}
