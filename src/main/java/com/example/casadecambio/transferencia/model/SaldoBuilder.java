package com.example.casadecambio.transferencia.model;

import java.math.BigDecimal;

public class SaldoBuilder {

    private String cpf;
    private BigDecimal valor;

    public SaldoBuilder setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public SaldoBuilder setValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public Saldo createSaldo() {
        return new Saldo(cpf, valor);
    }
}