package com.gympartner.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "routines")
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "indication", nullable = false)
    private String indication;

    @Column(name = "tips", nullable = false)
    private String tips;

    @Column(name = "url", nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "coach_id", nullable = false)
    //@JsonIgnore
    private Coach coach;

    @Column(name = "calories", nullable = false)
    private Integer calories;


    public Routine(String name, String description, String indication, String tips, String url, Coach coach, Integer calories) {
        this.name = name;
        this.description = description;
        this.indication = indication;
        this.tips = tips;
        this.url = url;
        this.coach = coach;
        this.calories = calories;

    }
}
