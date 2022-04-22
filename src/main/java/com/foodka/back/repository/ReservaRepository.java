package com.foodka.back.repository;

import com.foodka.back.domain.collection.Reserva;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReservaRepository extends ReactiveMongoRepository<Reserva, String> {

}
