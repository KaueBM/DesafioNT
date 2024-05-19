package com.dev.desafio.service;

import com.dev.desafio.converter.HotelConverter;
import com.dev.desafio.model.Hotel;
import com.dev.desafio.model.dto.HotelDTO;
import com.dev.desafio.repository.HotelCriteria;
import com.dev.desafio.repository.HotelRepository;
import com.dev.desafio.repository.HotelSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private HotelRepository repository;

    @Autowired
    private HotelConverter converter;

    public List<Hotel> buscaTodosHoteis() {
        return Optional.of(repository.findAll()).orElse(new ArrayList<>());
    }

    public Hotel buscaPorID(int id) {
        Optional<Hotel> hotel = repository.findById(id);

        if (hotel.isPresent()) {
            return hotel.get();
        } else {
            throw new RuntimeException("Não existe hotel com o ID " + id + " cadastrado no banco");
        }
    }

    public Hotel criarHotel(HotelDTO dados) {
        Optional<Hotel> hotel = repository.findById(dados.getId());

        if (hotel.isPresent()) {
            throw new RuntimeException("Hotel com o ID " + dados.getId() + " já existe.");
        } else {
            Hotel novoHotel = converter.toHotel(dados);
            repository.save(novoHotel);
            return novoHotel;
        }
    }

    public void atualizaHotel(HotelDTO dados) {
        Optional.of(repository.findById(dados.getId())).get().ifPresent(hotel -> {
            Hotel novoHotel = converter.toHotel(dados);
            repository.save(novoHotel);
        });
    }

    public void deletaHotel(int id) {
        Optional<Hotel> hotel = repository.findById(id);

        if (hotel.isPresent()) {
            repository.delete(hotel.get());
        } else {
            throw new RuntimeException("Não existe hotel com o ID " + id + " cadastrado no banco");
        }
    }

    public List<Hotel> pesquisarHoteis(HotelCriteria criteria) {
        return repository.findAll(HotelSpecification.buscaDeHoteis(criteria));
    }

}