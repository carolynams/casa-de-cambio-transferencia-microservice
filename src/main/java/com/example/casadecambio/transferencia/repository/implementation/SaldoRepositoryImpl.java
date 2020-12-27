package com.example.casadecambio.transferencia.repository.implementation;

import com.example.casadecambio.transferencia.model.Saldo;
import com.example.casadecambio.transferencia.repository.SaldoRepository;
import com.example.casadecambio.transferencia.repository.jpa.SaldoRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SaldoRepositoryImpl implements SaldoRepository {

    @Autowired
    private SaldoRepositoryJpa repositoryJpa;

    @Override
    public Saldo update(Saldo saldo) {
        return repositoryJpa.save(saldo);
    }

    @Override
    public Saldo findByCpf(String cpf) {
        return repositoryJpa.findByCpf(cpf);
    }

    @Override
    public Saldo create(Saldo saldo) {
        return repositoryJpa.save(saldo);
    }
}
