package org.project.community_nihon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // AuditingEntityListener 활성화
public class CommunityNihonApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityNihonApplication.class, args);
    }

}
