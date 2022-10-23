package com.gympartner.repository;

import com.gympartner.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Transactional(readOnly = true)
    List<Client> findByCoachId(Long coachId);
    
}
