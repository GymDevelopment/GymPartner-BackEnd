package com.gympartner.repository;

import com.gympartner.entities.AssignedRoutine;
import com.gympartner.entities.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AssignedRoutineRepository extends JpaRepository<AssignedRoutine, Long> {


    @Transactional(readOnly = true)
    List<AssignedRoutine> findByClientId(Long clientId);


    @Query("SELECT a.routine FROM AssignedRoutine a WHERE a.client.id = ?1")
    List<Routine> findAllRoutinesByClientIdJPQL(Long clientId);


    @Query("SELECT a.routine FROM AssignedRoutine a WHERE a.client.id = ?1 and a.done = false")
    List<Routine> findAllRoutinesByClientIdJPQLToday(Long clientId);

    @Query("SELECT a FROM AssignedRoutine a WHERE a.client.id = ?1 and a.date = current_date and a.done = false")
    List<AssignedRoutine> findAllTodayAssignedRoutinesByClientIdJPQL(Long clientId);
    @Query("SELECT a FROM AssignedRoutine a WHERE a.client.id = ?1 and cast(a.date as date) > current_date")
    List<AssignedRoutine> findAllFutureAssignedRoutinesByClientIdJPQL(Long clientId);
}
