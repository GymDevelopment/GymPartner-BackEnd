package com.gympartner.repository;

import com.gympartner.entities.AssignedDiet;
import com.gympartner.entities.Diet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AssignedDietRepository extends JpaRepository<AssignedDiet, Long> {
    @Transactional(readOnly = true)
    List<AssignedDiet> findByClientId(Long clientId);

    @Query("SELECT a.diet FROM AssignedDiet a WHERE a.client.id = ?1")
    List<Diet> findAllDietsByClientIdJPQL(Long clientId);
}
