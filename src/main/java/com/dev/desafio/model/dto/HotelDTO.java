package com.dev.desafio.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HotelDTO {

    private int id;
    private String nome;
    private int capacidade;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private String cep;
    private int numeroDeQuartos;

}