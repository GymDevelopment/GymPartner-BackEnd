package com.gympartner.converter;

import com.gympartner.dto.ClientResponseDTO;
import com.gympartner.entities.Client;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
@Component
public class ClientConverter {
    private final ModelMapper modelMapper;
    public ClientConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public ClientResponseDTO convertEntityToDto(Client client) {
        System.out.println(modelMapper.map(client, ClientResponseDTO.class));
        return modelMapper.map(client, ClientResponseDTO.class);
    }
}
