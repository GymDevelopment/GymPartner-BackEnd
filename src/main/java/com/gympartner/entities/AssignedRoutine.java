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
@Table(name = "assigned_routines")

public class AssignedRoutine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "routine_id", nullable = false)
    //@JsonIgnore
    private Routine routine;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore
    private Client client;

    @Column(name = "done", nullable = false)
    private Boolean done;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    public AssignedRoutine(Routine routine, Client client, Boolean done, Integer duration) {
        this.routine = routine;
        this.client = client;
        this.done = done;
        this.duration = duration;
    }
}
