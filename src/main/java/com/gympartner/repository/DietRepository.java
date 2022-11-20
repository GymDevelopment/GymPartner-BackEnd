package com.gympartner.repository;

import com.gympartner.entities.Diet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DietRepository extends JpaRepository<Diet, Long> {
    @Query("SELECT d FROM Diet d WHERE d.coach.id = ?1 AND lower(d.mealType) = 'breakfast'")
    List<Diet> findAllBreakfastByCoachIdJPQL(Long coachId);
    @Query("SELECT d FROM Diet d WHERE d.coach.id = ?1 AND lower(d.mealType) = 'lunch'")
    List<Diet> findAllLunchByCoachIdJPQL(Long coachId);
    @Query("SELECT d FROM Diet d WHERE d.coach.id = ?1 AND lower(d.mealType) = 'dinner'")
    List<Diet> findAllDinnerByCoachIdJPQL(Long coachId);
    @Query("SELECT d FROM Diet d WHERE lower(d.mealType) = 'breakfast'")
    List<Diet> findAllBreakfastJPQL();
    @Query("SELECT d FROM Diet d WHERE lower(d.mealType) = 'lunch'")
    List<Diet> findAllLunchJPQL();
    @Query("SELECT d FROM Diet d WHERE lower(d.mealType) = 'dinner'")
    List<Diet> findAllDinnerJPQL();
}
