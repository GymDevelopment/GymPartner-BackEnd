package com.gympartner.repository;

import com.gympartner.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Transactional(readOnly = true)

    @Query(value = "SELECT * FROM clients c WHERE c.coach_id = ?1", nativeQuery = true)
    List<Client> findAllClientByCoachIdSQL(Long coachId);

    @Query("FROM Client c WHERE c.coach.id = :coachId AND (LOWER(c.name) LIKE %:name% OR LOWER(c.lastName) LIKE %:lastName%)")
    List<Client> search(@Param("coachId") Long coachId, @Param("name") String name, @Param("lastName") String lastName);

    @Query("SELECT c FROM Client c WHERE c.coach.id = ?1")
    List<Client> findAllClientByCoachIdJPQL(Long coachId);

    List<Client> findByCoachId(Long coachId);

    Optional<Client> findByEmailAndPassword(String email, String password);

    @Query(value = "select * from fn_physical_state_report()", nativeQuery = true)
    List<Object[]> callProcedureOrFunction();

}
