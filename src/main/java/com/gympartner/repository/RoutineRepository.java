package com.gympartner.repository;

import com.gympartner.entities.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoutineRepository extends JpaRepository<Routine, Long> {
    List<Routine> findByCoachId(Long coachId);
}
