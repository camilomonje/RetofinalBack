package com.foodka.back.services.implement;

import com.foodka.back.domain.collection.Reserva;
import com.foodka.back.domain.dto.ReservaDTO;
import com.foodka.back.repository.ReservaRepository;
import com.foodka.back.services.ReservaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ReservaImplementService implements ReservaService {

    @Autowired
    ReservaRepository reservaRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public Mono<ReservaDTO> save(ReservaDTO reservaDTO) {
        return reservaRepository.save(modelMapper.map(reservaDTO, Reserva.class))
                .flatMap(r -> Mono.just(modelMapper.map(r, ReservaDTO.class)));
    }

    @Override
    public Mono<ReservaDTO> findById(String id) {
        return reservaRepository.findById(id)
                .flatMap(r -> Mono.just(modelMapper.map(r, ReservaDTO.class)))
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Flux<ReservaDTO> findAll() {
        return reservaRepository.findAll()
                .flatMap(r -> Mono.just(modelMapper.map(r, ReservaDTO.class)));
    }

    @Override
    public Mono<ReservaDTO> delete(String id) {
        return findById(id)
                .flatMap(r -> reservaRepository.deleteById(r.getId()).thenReturn(r));
    }

    @Override
    public Mono<ReservaDTO> update(String id, ReservaDTO reservaDTO) {
        return findById(id).
                flatMap(rDto -> {
                    reservaDTO.setId(rDto.getId());
                    return save(reservaDTO);
                })
                .switchIfEmpty(Mono.empty());
    }

    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher matcher = pattern.matcher(email);

        return matcher.find();
    }
}
