package com.foodka.back.controller;

import com.foodka.back.domain.dto.ReservaDTO;
import com.foodka.back.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/reserva")
public class ReservaController {

    @Autowired
    ReservaService reservaService;

    @PostMapping("")
    public ResponseEntity<Mono<ReservaDTO>> save(@RequestBody ReservaDTO reservaDTO) {
        System.out.println(reservaDTO);
        return new ResponseEntity<Mono<ReservaDTO>>(reservaService.save(reservaDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ReservaDTO>> findById(@PathVariable("id") String id) {
        return reservaService.findById(id)
                .flatMap(reserva -> Mono.just(ResponseEntity.ok(reserva)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping
    public ResponseEntity<Flux<ReservaDTO>> findAll() {
        return new ResponseEntity<Flux<ReservaDTO>>(reservaService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<ReservaDTO>> delete(@PathVariable("id") String id) {
        return reservaService.delete(id)
                .flatMap(reserva -> Mono.just(ResponseEntity.ok(reserva)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ReservaDTO>> update(@PathVariable("id") String id, @RequestBody ReservaDTO reservaDTO) {
        return reservaService.update(id, reservaDTO)
                .flatMap(reserva -> Mono.just(ResponseEntity.ok(reserva)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

}
