package org.project.community_nihon.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass // 공통으로 사용되는 칼럼 지정 후 상속
@EntityListeners(value = {AuditingEntityListener.class})  // 엔티티가 DB에 추가되거나 변경될 때 자동으로 시간 값 지정
@Getter
abstract class BaseEntity {

    @CreatedDate
    @Column(name = "created_time", updatable = false)
    private LocalDateTime created_time;

    //@LastModifiedDate
    //@Column(name = "modified_at")
    //private LocalDateTime modified_at;

}
