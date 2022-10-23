package com.gympartner.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "diets")
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "meal", length = 20, nullable = false)
    private String meal;

    @Column(name = "indication", nullable = false)
    private String indication;

    @Column(name = "calories", nullable = false)
    private Integer calories;

    @Column(name = "hour", nullable = false)
    private Integer hour;

    @ManyToOne
    @JoinColumn(name = "coach_id", nullable = false)
    private Coach coach;

    public Diet(String name, String meal, String indication, Integer calories, Integer hour, Coach coach) {
        this.name = name;
        this.meal = meal;
        this.indication = indication;
        this.calories = calories;
        this.hour = hour;
        this.coach = coach;
    }
}
