package com.dev.desafio.converter;

import com.dev.desafio.model.Hotel;
import com.dev.desafio.model.dto.HotelDTO;
import org.springframework.stereotype.Component;

@Component
public class HotelConverter {

    public Hotel toHotel(HotelDTO dto){
        return Hotel.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .capacidade(dto.getCapacidade())
                .estado(dto.getEstado())
                .cidade(dto.getCidade())
                .bairro(dto.getBairro())
                .rua(dto.getRua())
                .cep(dto.getCep())
                .numeroDeQuartos(dto.getNumeroDeQuartos())
                .build();
    }

}