package com.dev.desafio.repository;

import com.dev.desafio.model.Hotel;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class HotelSpecification {

    public static Specification<Hotel> buscaDeHoteis(HotelCriteria criteria) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (criteria.getDestino() != null) {
                predicates.add(builder.equal(root.get("destino"), criteria.getDestino()));
            }
            if (criteria.getDataDeChegada() != null && criteria.getDataDeSaida() != null) {
                predicates.add(builder.between(root.get("disponivel"), criteria.getDataDeChegada(), criteria.getDataDeSaida()));
            }
            if (criteria.getNumerosDeQuartos() > 0) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("quartos"), criteria.getNumerosDeQuartos()));
            }
            if (criteria.getNumeroDePessoas() > 0) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("capacidade"), criteria.getNumeroDePessoas()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}