package com.dev.desafio.repository;

import com.dev.desafio.model.Hotel;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HotelSpecificationTest {
    @Mock
    private Root<Hotel> root;

    @Mock
    private CriteriaQuery<?> query;

    @Mock
    private CriteriaBuilder builder;

    @Test
    void testBuscaDeHoteis() {
        HotelCriteria criteria = new HotelCriteria();
        criteria.setDestino("Paris");
        criteria.setDataDeChegada(LocalDate.of(2024, 6, 1));
        criteria.setDataDeSaida(LocalDate.of(2024, 6, 7));
        criteria.setNumerosDeQuartos(2);
        criteria.setNumeroDePessoas(4);

        Specification<Hotel> specification = HotelSpecification.buscaDeHoteis(criteria);

        List<Predicate> predicates = new ArrayList<>();
        Predicate destinoPredicate = mock(Predicate.class);
        Predicate disponivelPredicate = mock(Predicate.class);
        Predicate quartosPredicate = mock(Predicate.class);
        Predicate capacidadePredicate = mock(Predicate.class);

        when(builder.equal(root.get("destino"), "Paris")).thenReturn(destinoPredicate);
        when(builder.between(root.get("disponivel"), LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 7))).thenReturn(disponivelPredicate);
        when(builder.greaterThanOrEqualTo(root.get("quartos"), 2)).thenReturn(quartosPredicate);
        when(builder.greaterThanOrEqualTo(root.get("capacidade"), 4)).thenReturn(capacidadePredicate);

        predicates.add(destinoPredicate);
        predicates.add(disponivelPredicate);
        predicates.add(quartosPredicate);
        predicates.add(capacidadePredicate);

        when(builder.and(predicates.toArray(new Predicate[0]))).thenReturn(mock(Predicate.class));

        specification.toPredicate(root, query, builder);

        verify(builder).and(predicates.toArray(new Predicate[0]));

        Assertions.assertEquals(4, predicates.size());
    }

}