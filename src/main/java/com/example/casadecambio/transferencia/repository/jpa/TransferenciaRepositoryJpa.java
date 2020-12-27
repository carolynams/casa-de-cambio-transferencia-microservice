package com.example.casadecambio.transferencia.repository.jpa;

import com.example.casadecambio.transferencia.model.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferenciaRepositoryJpa extends JpaRepository<Transferencia, Long> {
}
