package com.dev.desafio.controller;

import com.dev.desafio.model.dto.HotelDTO;
import com.dev.desafio.service.HotelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.dev.desafio.mock.Constantes.NOME_DO_HOTEL;
import static com.dev.desafio.mock.HotelMock.criaHotel;
import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HotelController.class)
@ExtendWith(MockitoExtension.class)
class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelService service;

    @Test
    void deveVoltarListaDeHoteisComSucesso() throws Exception {
        when(service.buscaTodosHoteis()).thenReturn(singletonList(criaHotel()));

        mockMvc.perform(get("/hotel")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value(NOME_DO_HOTEL));
    }

    @Test
    void deveAcharHotelPorIdComSucesso() throws Exception {
        when(service.buscaPorID(1)).thenReturn(criaHotel());

        mockMvc.perform(get("/hotel/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value(NOME_DO_HOTEL));
    }

    @Test
    void deveCriarHotelComSucesso() throws Exception {
        when(service.criarHotel(any(HotelDTO.class))).thenReturn(criaHotel());

        mockMvc.perform(post("/hotel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Hotel com outro nome\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value(NOME_DO_HOTEL));
    }

    @Test
    void deveAtualizarHotelComSucesso() throws Exception {
        mockMvc.perform(put("/hotel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"nome\": \"Hotel Atualizado com sucesso\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void deveDeletarHotelComSucesso() throws Exception {
        mockMvc.perform(delete("/hotel/{id}", 1))
                .andExpect(status().isOk());
    }
}