package com.foodka.back.domain.dto;


import com.foodka.back.domain.values.Cliente;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
public class ReservaDTO {

    private String id = UUID.randomUUID().toString().substring(0, 10);
    private String mensaje;
    private String hora;
    private String dia;
    private Cliente cliente;

    public ReservaDTO() {
    }


    public ReservaDTO(String id, String mensaje, String hora, String dia, Cliente cliente) {
        this.id = id;
        this.mensaje = mensaje;
        this.hora = hora;
        this.dia = dia;
        this.cliente = cliente;
    }



}
