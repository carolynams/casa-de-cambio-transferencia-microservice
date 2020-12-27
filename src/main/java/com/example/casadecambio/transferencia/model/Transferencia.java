package com.example.casadecambio.transferencia.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TRANSFERENCIA")
public class Transferencia {

    public static final String TRANSFERENCIA_REALIZADA = "Transferencia realizada com sucesso!";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataDaTransferencia;

    private String cpf;

    private BigDecimal valor;

    public Transferencia() {
    }

    public Transferencia(String cpf, BigDecimal valor) {
        this.cpf = cpf;
        this.valor = valor;
        this.dataDaTransferencia = LocalDateTime.now();
    }
}
