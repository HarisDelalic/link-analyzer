package com.haris.linkanalyzer.repository;

import com.haris.linkanalyzer.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
