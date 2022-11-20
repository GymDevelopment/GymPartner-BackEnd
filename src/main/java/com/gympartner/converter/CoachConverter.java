package com.gympartner.converter;

import com.gympartner.dto.CoachResponseDTO;
import com.gympartner.entities.Coach;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
@Component
public class CoachConverter {
    private final ModelMapper modelMapper;
    public CoachConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public CoachResponseDTO convertEntityToDto(Coach coach) {
        return modelMapper.map(coach, CoachResponseDTO.class);
    }
}