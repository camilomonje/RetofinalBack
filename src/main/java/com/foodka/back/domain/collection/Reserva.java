package com.foodka.back.domain.collection;

import com.foodka.back.domain.values.Cliente;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Document(collection = "reservas")
public class Reserva {

    @Id
    private String id = UUID.randomUUID().toString().substring(0, 10);
    private String mensaje;
    private LocalTime hora;
    private LocalDate dia;
    private Cliente cliente;

    public Reserva() {
    }


    public Reserva(String id, String mensaje, LocalTime hora, LocalDate dia, Cliente cliente) {
        this.id = id;
        this.mensaje = mensaje;
        this.hora = hora;
        this.dia = dia;
        this.cliente = cliente;
    }
}
