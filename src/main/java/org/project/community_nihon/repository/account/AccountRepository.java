package org.project.community_nihon.repository.account;


import org.project.community_nihon.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
