package com.example.casadecambio.transferencia.repository;

import com.example.casadecambio.transferencia.model.Saldo;

public interface SaldoRepository {

    Saldo update(Saldo saldo);

    Saldo findByCpf(String cpf);

    Saldo create(Saldo saldo);
}
