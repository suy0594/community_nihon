package org.project.community_nihon.security;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class persistent_logins {

    @Id
    private String series;

    private String username;
    private String token;
    private LocalDateTime last_used;

}
