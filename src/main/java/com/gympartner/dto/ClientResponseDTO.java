package com.gympartner.dto;

import com.gympartner.entities.Coach;
import com.gympartner.entities.Gym;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDTO {
    private Long id;
    private String name;
    private String lastName;
    private Gym gym;
    private String email;
    private String personalGoal;
    private LocalDate birthday;
    private String physicalState;
    private Integer tall;
    private Integer weight;
    private Coach coach;
    private String phone;
}
