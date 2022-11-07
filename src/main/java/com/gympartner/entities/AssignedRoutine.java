package com.gympartner.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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
    //@JsonIgnore
    private Client client;
    @ManyToOne
    @JoinColumn(name = "coach_id", nullable = false)
    private Coach coach;
    @Column(name = "done", nullable = false)
    private Boolean done;

    //@Column(name = "date", nullable = false)
    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false, columnDefinition = "DATE")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    public AssignedRoutine(Routine routine, Client client, Boolean done, Integer duration, LocalDate date,  Coach coach) {
        this.routine = routine;
        this.client = client;
        this.coach = coach;
        this.done = done;
        this.duration = duration;
        this.date = date;
    }
}
