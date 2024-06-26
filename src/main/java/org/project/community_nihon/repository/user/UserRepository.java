package org.project.community_nihon.repository.user;

import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.domain.user.UserVO;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserVO, String> {

    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from UserVO m where m.id = :id and m.social = false")
    Optional<UserVO> getWithRoles(String id);

    @Query("SELECT u.origin FROM UserVO u WHERE u.id = :id")
    Account findAccountByUserId(@Param("id") String id);

    @Query("SELECT n.id from UserVO n where n.origin = :origin")
    String getUserByAccount(Account origin);
}
