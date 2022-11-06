package com.gympartner.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "gyms")
public class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "address", length = 100, nullable = false)
    private String address;
    //
    // @Column(name = "consult_date",nullable = false)
    @Column(name = "consult_date", columnDefinition = "DATE")
    private LocalDateTime consultDate;
    public Gym(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
