package com.dev.desafio.mock;

import com.dev.desafio.model.Hotel;
import com.dev.desafio.model.dto.HotelDTO;

import static com.dev.desafio.mock.Constantes.*;

public class HotelMock {

    public static Hotel criaHotel() {
        return Hotel.builder()
                .id(ID)
                .nome(NOME_DO_HOTEL)
                .capacidade(CAPACIDADE)
                .estado(ESTADO)
                .cidade(CIDADE)
                .bairro(BAIRRO)
                .rua(RUA)
                .cep(CEP)
                .numeroDeQuartos(NUMERO)
                .build();
    }

    public static HotelDTO criaHotelDTO() {
        return HotelDTO.builder()
                .id(ID)
                .nome(NOME_DO_HOTEL)
                .capacidade(CAPACIDADE)
                .estado(ESTADO)
                .cidade(CIDADE)
                .bairro(BAIRRO)
                .rua(RUA)
                .cep(CEP)
                .numeroDeQuartos(NUMERO)
                .build();
    }
}
