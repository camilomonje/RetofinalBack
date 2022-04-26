package com.foodka.back.services.implement;

import com.foodka.back.domain.collection.Reserva;
import com.foodka.back.domain.dto.ReservaDTO;
import com.foodka.back.repository.ReservaRepository;
import com.foodka.back.services.EmailService;
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

    @Autowired
    EmailService emailService;

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

    @Override
    public Mono<String> sendNotificationEmail(ReservaDTO reservaDTO) {
        return emailService.sendEmailMessage(
                reservaDTO.getCliente().getEmail(),
                String.format("Confirmación de Reserva Restaurante FOODKA Código: %s", reservaDTO.getId()),
                String.format("Buen día %s, %n" +
                                "Su reserva fue confirmada para el día %s, hora %s para %d personas. %n" +
                                "Su pedido fue: %s. %n%n" +
                                "Tienes hasta dos horas antes de tu reserva para cancelar o modificar la misma. %n" +
                                "¡Los esperamos!",
                        reservaDTO.getCliente().getNombre().toUpperCase(),
                        reservaDTO.getDia(),
                        reservaDTO.getHora(),
                        reservaDTO.getCantidadPersonas(),
                        reservaDTO.getMensaje()));
    }
}

//Buen dia Lucia,
//Su reserva fue confirmada para el dia 22/1/2012, hora 10:00 para 10 personas.
//Su pedido fue: mensaje
//Tienes hasta dos horas antes de tu reserva para cancelar o modificar la misma.
//¡Los esperamos!