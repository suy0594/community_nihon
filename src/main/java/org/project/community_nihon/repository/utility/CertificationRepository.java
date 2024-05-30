package org.project.community_nihon.repository.utility;

import org.project.community_nihon.domain.account.Account;
import org.project.community_nihon.domain.community.Community;
import org.project.community_nihon.domain.utility.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CertificationRepository extends JpaRepository<Certification, Long> {

    @Query("SELECT c FROM Certification c JOIN c.community com WHERE com.community = :id")
    List<Certification> findByCommunityId(@Param("id") Long id);

    @Query("SELECT c FROM Certification c JOIN c.master m WHERE m.id = :id")
    List<Certification> findByMasterId(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Certification c WHERE c.community = :community AND c.master = :master")
    void deleteByCommunityAndMaster(@Param("community") Community community, @Param("master") Account master);

}
