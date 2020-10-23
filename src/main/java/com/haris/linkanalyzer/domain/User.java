package com.haris.linkanalyzer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long id;
    private String email;
    private String username;
    @JsonIgnore
    private String password;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<Link> links;
}
