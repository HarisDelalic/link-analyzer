package com.haris.linkanalyzer.repository;

import com.haris.linkanalyzer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
