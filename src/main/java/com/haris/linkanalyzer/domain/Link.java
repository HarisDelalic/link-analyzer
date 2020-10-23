package com.haris.linkanalyzer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    Long id;

    @NotNull
    String value;
    String parsedContent;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
}
