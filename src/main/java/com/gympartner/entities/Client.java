package com.gympartner.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;
    @ManyToOne
    @JoinColumn(name = "gym_id", nullable = false)
    //@JsonIgnore
    private Gym gym;
    @Column(name = "password", length = 20, nullable = false)
    private String password;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "personal_goal", nullable = false)
    private String personalGoal;

    //@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    /*
    @JsonDeserialize(as = LocalDateTime.class)
    @Past
    private LocalDateTime birthday;
     */
    @Column(name = "birthday", nullable = false, columnDefinition = "DATE")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthday;
    @Column(name = "physical_state", length = 50, nullable = false)
    private String physicalState;
    @Column(name = "tall", nullable = false)
    private Integer tall;
    @Column(name = "weight", nullable = false)
    private Integer weight;

    @ManyToOne
    @JoinColumn(name = "coach_id", nullable = false)
    //@JsonIgnore
    private Coach coach;

    @Column(name = "phone", length = 12, nullable = false)
    private String phone;

    public Client(String name, String lastName, Gym gym, String password, String email, String personalGoal, LocalDate birthday, String physicalState, Integer tall, Integer weight, Coach coach, String phone) {
        this.name = name;
        this.lastName = lastName;
        this.gym = gym;
        this.password = password;
        this.email = email;
        this.personalGoal = personalGoal;
        this.birthday = birthday;
        this.physicalState = physicalState;
        this.tall = tall;
        this.weight = weight;
        this.coach = coach;
        this.phone = phone;
    }
}
