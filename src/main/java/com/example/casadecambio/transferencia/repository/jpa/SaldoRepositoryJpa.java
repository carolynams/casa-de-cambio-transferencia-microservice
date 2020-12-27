package com.example.casadecambio.transferencia.repository.jpa;

import com.example.casadecambio.transferencia.model.Saldo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaldoRepositoryJpa extends JpaRepository<Saldo, Long> {

    Saldo findByCpf(String cpf);
}
