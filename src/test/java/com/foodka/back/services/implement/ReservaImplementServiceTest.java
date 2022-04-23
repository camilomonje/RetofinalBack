package com.foodka.back.services.implement;

import com.foodka.back.domain.dto.ReservaDTO;
import com.foodka.back.domain.values.Cliente;
import com.foodka.back.services.ReservaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservaImplementServiceTest {

    @Autowired
    ReservaService service;

    @Test
    void testSaveReserva() {
        ReservaDTO reserva = new ReservaDTO( "","12:00","23/04/2022",new Cliente());
        Mono<ReservaDTO> reserva2 = service.save(reserva);
        StepVerifier.create(reserva2).expectNext(reserva).verifyComplete();
    }

    @Test
    void testFindById() {
        ReservaDTO reserva = new ReservaDTO( "","12:00","23/04/2022",new Cliente());
        Mono<ReservaDTO> reservaDTOMono = service.save(reserva);
        String id = reservaDTOMono.block().getId();
        Mono<ReservaDTO> reservaDTOMono1 = service.findById(id);
        StepVerifier.create(reservaDTOMono1).expectNext(reserva).verifyComplete();
    }

}