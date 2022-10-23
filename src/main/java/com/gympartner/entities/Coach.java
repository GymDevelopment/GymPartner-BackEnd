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
@Table(name = "coaches")
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "lastName", length = 50, nullable = false)
    private String lastName;

    @Column(name = "password", length = 20, nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "gym_id", nullable = false)
    //@JsonIgnore
    private Gym gym;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "phone", length = 12, nullable = false)
    private String phone;

    public Coach(String name, String lastName, String password, Gym gym, String email, String phone) {
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.gym = gym;
        this.email = email;
        this.phone = phone;
    }
}
