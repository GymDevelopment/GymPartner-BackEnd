package com.gympartner.repository;

import com.gympartner.entities.Client;
import com.gympartner.entities.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoachRepository extends JpaRepository<Coach, Long> {
    Optional<Coach> findByEmailAndPassword(String email, String password);
}
