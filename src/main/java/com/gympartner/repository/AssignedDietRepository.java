package com.gympartner.repository;

import com.gympartner.entities.AssignedDiet;
import com.gympartner.entities.Diet;
import com.gympartner.entities.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AssignedDietRepository extends JpaRepository<AssignedDiet, Long> {

    @Transactional(readOnly = true)
    List<AssignedDiet> findByClientId(Long clientId);

    @Query("SELECT a FROM AssignedDiet a WHERE a.client.id = ?1 AND a.date = CURRENT_DATE")
    AssignedDiet findAssignedDietByClientIdJPQL(Long clientId);

    @Query("SELECT a.breakfast FROM AssignedDiet a WHERE a.client.id = ?1")
    List<Diet> findAllBreakfastByClientIdJPQL(Long clientId);
    @Query("SELECT a.lunch FROM AssignedDiet a WHERE a.client.id = ?1")
    List<Diet> findAllLunchByClientIdJPQL(Long clientId);
    @Query("SELECT a.dinner FROM AssignedDiet a WHERE a.client.id = ?1")
    List<Diet> findAllDinnerByClientIdJPQL(Long clientId);
}
