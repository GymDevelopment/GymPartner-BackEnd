package com.gympartner.entities;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
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
    @JoinColumn(name = "breakfast_id", nullable = false)
    //@JsonIgnore
    private Diet breakfast;
    @ManyToOne
    @JoinColumn(name = "lunch_id", nullable = false)
    //@JsonIgnore
    private Diet lunch;
    @ManyToOne
    @JoinColumn(name = "dinner_id", nullable = false)
    //@JsonIgnore
    private Diet dinner;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    //@JsonIgnore
    private Client client;
    //@Column(name = "date", nullable = false)
    @Column(name = "date", nullable = false, columnDefinition = "DATE")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "coach_id", nullable = false)
    private Coach coach;
    @Column(name = "done", nullable = false)
    private Boolean done;
    public AssignedDiet(Diet breakfast, Diet lunch, Diet dinner,  Client client, Coach coach, LocalDate date, Boolean done) {
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.client = client;
        this.coach = coach;
        this.date = date;
        this.done = done;
    }
}
