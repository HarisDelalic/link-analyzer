package com.haris.linkanalyzer.repository;

import com.haris.linkanalyzer.domain.Link;
import com.haris.linkanalyzer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface LinkRepository extends JpaRepository<Link, Long> {
    Set<Link> findAllByUser(User user);
}
