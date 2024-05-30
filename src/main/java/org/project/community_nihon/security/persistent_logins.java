package org.project.community_nihon.security;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.project.community_nihon.domain.BaseEntity_Modified_Time;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class persistent_logins extends BaseEntity_Modified_Time {

    @Id
    private String series;

    private String username;
    private String token;

}
