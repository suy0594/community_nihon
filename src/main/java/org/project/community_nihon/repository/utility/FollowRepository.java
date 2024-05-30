package org.project.community_nihon.repository.utility;

import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.domain.utility.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Follow f WHERE f.origin = :origin AND f.follow = :follows")
    boolean existsByOriginAndFollows(Account origin, Account follows);

    @Query("SELECT f.origin FROM Follow f WHERE f.follow = :follow")
    Account findOriginsByFollow(Account follow);

    @Query("SELECT f FROM Follow f WHERE f.origin = :origin AND f.follow = :follow")
    Follow findByOriginAndFollow(Account origin, Account follow);

    @Query("SELECT count(f) FROM Follow f WHERE f.origin = :account")
    int countFollowsByOrigin(Account account);

    @Query("SELECT count(f) FROM Follow f WHERE f.follow = :account")
    int countOriginsByFollow(Account account);

    @Query("SELECT count(f) FROM Follow f WHERE f.follow = :account AND f.friend = true")
    int countFriendOriginsByFollow(Account account);

    @Query("SELECT count(f) FROM Follow f WHERE f.origin = :account AND f.friend = true")
    int countFriendFollowsByOrigin(Account account);

}
