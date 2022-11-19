package com.gympartner.repository;

import com.gympartner.entities.AssignedRoutine;
import com.gympartner.entities.Client;
import com.gympartner.entities.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface AssignedRoutineRepository extends JpaRepository<AssignedRoutine, Long> {


    @Transactional(readOnly = true)
    List<AssignedRoutine> findByClientId(Long clientId);

    @Query("SELECT a FROM AssignedRoutine a WHERE a.client.id = ?1 ORDER BY a.date")
    List<AssignedRoutine> findByClientIdJPQL(Long clientId);

    @Query(value = "SELECT * FROM assigned_routines WHERE client_id = ?1 AND done = true AND date BETWEEN CURRENT_DATE - 7 AND CURRENT_DATE ORDER BY date;", nativeQuery = true)
    List<AssignedRoutine> findByClientIdSQLReport(Long clientId);


    @Query("SELECT a.routine FROM AssignedRoutine a WHERE a.client.id = ?1")
    List<Routine> findAllRoutinesByClientIdJPQL(Long clientId);

    @Query("SELECT a.client FROM AssignedRoutine a WHERE a.routine.id = ?1")
    List<Client> findAllClientsByRoutineIdJPQL(Long routineId);


    @Query("SELECT a.routine FROM AssignedRoutine a WHERE a.client.id = ?1 and a.done = false")
    List<Routine> findAllRoutinesByClientIdJPQLToday(Long clientId);

    @Query("SELECT a FROM AssignedRoutine a WHERE a.client.id = ?1 and a.date = current_date and a.done = false")
    List<AssignedRoutine> findAllTodayAssignedRoutinesByClientIdJPQL(Long clientId);
    @Query("SELECT a FROM AssignedRoutine a WHERE a.client.id = ?1 and cast(a.date as date) > current_date")
    List<AssignedRoutine> findAllFutureAssignedRoutinesByClientIdJPQL(Long clientId);

    @Query("FROM AssignedRoutine a WHERE a.client.id = :clientId AND a.date BETWEEN :date1 AND :date2")
    List<AssignedRoutine> searchByDates(@Param("clientId") Long clientId ,@Param("date1") LocalDate date1, @Param("date2") LocalDate date2);



}
