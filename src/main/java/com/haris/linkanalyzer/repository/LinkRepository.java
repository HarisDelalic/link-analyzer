package com.haris.linkanalyzer.repository;

import com.haris.linkanalyzer.domain.Link;
import com.haris.linkanalyzer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface LinkRepository extends JpaRepository<Link, Long> {
    Set<Link> findAllByUser(User user);

    Optional<Link> findByIdAndUser(Long linkId, User user);

    @Query("from Link l " +
            "inner join l.tags t " +
            "where t.value in :tagValues")
    Set<Link> linksByTags(@Param("tagValues") Set<String> tagValues);
}
