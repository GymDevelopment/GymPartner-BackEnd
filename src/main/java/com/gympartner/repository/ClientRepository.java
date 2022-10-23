package com.gympartner.repository;

import com.gympartner.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Transactional(readOnly = true)

    @Query(value = "SELECT * FROM clients c WHERE c.coach_id = ?1", nativeQuery = true)
    List<Client> findAllClientByCoachIdSQL(Long coachId);

    @Query("SELECT c FROM Client c WHERE c.coach.id = ?1")
    List<Client> findAllClientByCoachIdJPQL(Long coachId);

    List<Client> findByCoachId(Long coachId);

}
