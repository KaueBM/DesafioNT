package com.dev.desafio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity(name = "Hotel")
@Table(name = "Desafio")
@Data
@AllArgsConstructor
@Builder
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private int capacidade;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private String cep;
    private int numeroDeQuartos;

    public Hotel() {
    }
}