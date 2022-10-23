package com.gympartner.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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
    @Column(name = "lastName", length = 50, nullable = false)
    private String lastName;
    @ManyToOne
    @JoinColumn(name = "gym_id", nullable = false)
    //@JsonIgnore
    private Gym gym;
    @Column(name = "password", length = 20, nullable = false)
    private String password;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "personalGoal", nullable = false)
    private String personalGoal;

    @Column(name = "birthDate", nullable = false)
    private Date birthDate;

    @Column(name = "physicalState", length = 50, nullable = false)
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

    public Client(String name, String lastName, Gym gym, String password, String email, String personalGoal, Date birthDate, String physicalState, Integer tall, Integer weight, Coach coach, String phone) {
        this.name = name;
        this.lastName = lastName;
        this.gym = gym;
        this.password = password;
        this.email = email;
        this.personalGoal = personalGoal;
        this.birthDate = birthDate;
        this.physicalState = physicalState;
        this.tall = tall;
        this.weight = weight;
        this.coach = coach;
        this.phone = phone;
    }
}
