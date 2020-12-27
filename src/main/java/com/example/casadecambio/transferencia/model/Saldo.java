package com.example.casadecambio.transferencia.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

@Data
@Entity
@Table(name = "SALDO")
public class Saldo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private String cpf;

    public Saldo(String cpf) {
        this.cpf = cpf;
        this.valor = ZERO;
    }

    public Saldo() {
    }
}
