package com.gympartner.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultClientDTO {
    private String physicalState;
    private Integer quantity;
    private Integer routineDuration;
    private Double averageAge;
}
