package com.dev.desafio.service;

import com.dev.desafio.converter.HotelConverter;
import com.dev.desafio.model.Hotel;
import com.dev.desafio.model.dto.HotelDTO;
import com.dev.desafio.repository.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.dev.desafio.mock.Constantes.NOME_DO_HOTEL;
import static com.dev.desafio.mock.HotelMock.criaHotel;
import static com.dev.desafio.mock.HotelMock.criaHotelDTO;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HotelServiceTest {

    @Mock
    private HotelRepository repository;

    @Mock
    private HotelConverter converter;

    @InjectMocks
    private HotelService service;

    private Hotel hotel;
    private HotelDTO hotelDTO;

    @BeforeEach
    void setUp() {
        hotel = criaHotel();
        hotelDTO = criaHotelDTO();
    }

    @Test
    void testBuscaTodosHoteis() {
        when(repository.findAll()).thenReturn(singletonList(hotel));

        List<Hotel> hoteis = service.buscaTodosHoteis();

        assertFalse(hoteis.isEmpty());
        assertEquals(1, hoteis.size());
        verify(repository).findAll();
    }

    @Test
    void testBuscaPorIDExistente() {
        when(repository.findById(1)).thenReturn(Optional.of(hotel));

        Hotel hotel = service.buscaPorID(1);

        assertNotNull(hotel);
        assertEquals(NOME_DO_HOTEL, hotel.getNome());
        verify(repository).findById(1);
    }

    @Test
    void testBuscaPorIDNaoExistente() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.buscaPorID(1);
        });

        assertEquals("Não existe hotel com o ID 1 cadastrado no banco", exception.getMessage());
        verify(repository).findById(1);
    }

    @Test
    void testCriarHotelExistente() {
        when(repository.findById(1)).thenReturn(Optional.of(hotel));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.criarHotel(hotelDTO);
        });

        assertEquals("Hotel com o ID 1 já existe.", exception.getMessage());
        verify(repository).findById(1);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testCriarHotelNaoExistente() {
        when(repository.findById(1)).thenReturn(Optional.empty());
        when(converter.toHotel(hotelDTO)).thenReturn(hotel);
        when(repository.save(hotel)).thenReturn(hotel);

        Hotel createdHotel = service.criarHotel(hotelDTO);

        assertNotNull(createdHotel);
        assertEquals(NOME_DO_HOTEL, createdHotel.getNome());
        verify(repository).findById(1);
        verify(repository).save(hotel);
    }

    @Test
    void testAtualizaHotel() {
        when(repository.findById(1)).thenReturn(Optional.of(hotel));
        when(converter.toHotel(hotelDTO)).thenReturn(hotel);

        service.atualizaHotel(hotelDTO);

        verify(repository).findById(1);
        verify(repository).save(hotel);
    }

    @Test
    void testDeletaHotelExistente() {
        when(repository.findById(1)).thenReturn(Optional.of(hotel));

        service.deletaHotel(1);

        verify(repository).findById(1);
        verify(repository).delete(hotel);
    }

    @Test
    void testDeletaHotelNaoExistente() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.deletaHotel(1);
        });

        assertEquals("Não existe hotel com o ID 1 cadastrado no banco", exception.getMessage());
        verify(repository).findById(1);
        verify(repository, times(0)).delete(any(Hotel.class));
    }

}