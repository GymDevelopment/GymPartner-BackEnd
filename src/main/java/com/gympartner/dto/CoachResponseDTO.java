package com.gympartner.dto;

import com.gympartner.entities.Gym;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoachResponseDTO {
    private Long id;
    private String name;
    private String lastName;
    private Gym Gym;
    private String email;
    private String phone;
}
