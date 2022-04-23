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
    public ResponseEntity<Mono<ReservaDTO>> findById(@PathVariable("id") String id) {
        return new ResponseEntity<Mono<ReservaDTO>>(reservaService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Flux<ReservaDTO>> findAll() {
        return new ResponseEntity<Flux<ReservaDTO>>(reservaService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mono<ReservaDTO>> delete(@PathVariable("id") String id) {
        return new ResponseEntity<Mono<ReservaDTO>>(reservaService.delete(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mono<ReservaDTO>> update(@PathVariable("id") String id, @RequestBody ReservaDTO reservaDTO) {
        return new ResponseEntity<Mono<ReservaDTO>>(reservaService.update(id, reservaDTO), HttpStatus.OK);
    }

}
