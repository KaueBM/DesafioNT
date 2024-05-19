package com.dev.desafio.controller;

import com.dev.desafio.model.Hotel;
import com.dev.desafio.model.dto.HotelDTO;
import com.dev.desafio.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/hotel"})
public class HotelController {

    @Autowired
    private HotelService service;


    @GetMapping
    public ResponseEntity<List<Hotel>> buscarTodosHoteis() {
        return ResponseEntity.ok(service.buscaTodosHoteis());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity buscarHotelPorID(@PathVariable int id) {
        return ResponseEntity.ok(service.buscaPorID(id));
    }

    @PostMapping
    public Hotel cadastrarHotel(@RequestBody HotelDTO dados) {
        return service.criarHotel(dados);
    }

    @PutMapping()
    public ResponseEntity atualizarHotel(@RequestBody HotelDTO dados) {
        service.atualizaHotel(dados);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity removerHotel(@PathVariable int id) {
        service.deletaHotel(id);
        return ResponseEntity.ok().build();
    }


}