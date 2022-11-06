package com.gympartner.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "assigned_diets")
public class AssignedDiet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "diet_id", nullable = false)
    //@JsonIgnore
    private Diet diet;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    //@JsonIgnore
    private Client client;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "done", nullable = false)
    private Boolean done;

    @Column(name = "day", length = 20, nullable = false)
    private String day;

    public AssignedDiet(Diet diet, Client client, Date date, Boolean done, String day) {

        this.diet = diet;
        this.client = client;
        this.date = date;
        this.done = done;
        this.day = day;

    }
}
