package org.project.community_nihon.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
