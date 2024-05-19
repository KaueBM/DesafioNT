package com.dev.desafio.repository;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HotelCriteria {
    private String destino;
    private LocalDate dataDeChegada;
    private LocalDate dataDeSaida;
    private int numerosDeQuartos;
    private int numeroDePessoas;
}
