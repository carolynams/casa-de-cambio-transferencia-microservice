package com.example.casadecambio.transferencia.service;

import com.example.casadecambio.transferencia.exceptions.DataIntegrityViolationException;
import com.example.casadecambio.transferencia.model.Saldo;
import com.example.casadecambio.transferencia.model.SaldoBuilder;
import com.example.casadecambio.transferencia.model.Transferencia;
import com.example.casadecambio.transferencia.repository.SaldoRepository;
import com.example.casadecambio.transferencia.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.example.casadecambio.transferencia.exceptions.DataIntegrityViolationException.*;
import static com.example.casadecambio.transferencia.model.Transferencia.TRANSFERENCIA_REALIZADA;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class SaldoService {

    @Autowired
    private SaldoRepository repository;

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    public Saldo update(String cpf, BigDecimal valor) {
        Saldo saldo = validateSaldo(cpf);
        validateAmountToTransfer(cpf, valor, saldo);
        return repository.update(saldo);
    }

    public String transferencia(String cpfOrigem, String cpfDestino, BigDecimal valor) {
        if (valor.floatValue() < 0) {
            throw new DataIntegrityViolationException(VALOR_INVALIDO);
        }
        update(cpfOrigem, valor.negate());
        update(cpfDestino, valor);
        return TRANSFERENCIA_REALIZADA;
    }

    public Saldo create(String cpf) {
        checkIfCpfAlreadyExists(cpf);
        return repository.create(new Saldo(cpf));
    }

    private void checkIfCpfAlreadyExists(String cpf) {
        Saldo saldo = repository.findByCpf(cpf);
        if (nonNull(saldo)) {
            throw new DataIntegrityViolationException(CPF_JA_CADASTRADO);
        }
    }

    private Saldo validateSaldo(String cpf) {
        Saldo saldo = repository.findByCpf(cpf);
        if (isNull(saldo)) {
            throw new DataIntegrityViolationException(CPF_NAO_ENCONTRADO);
        }
        return saldo;
    }

    private void validateAmountToTransfer(String cpf, BigDecimal valor, Saldo saldo) {
        BigDecimal novoValor = saldo.getValor().add(valor);
        if (novoValor.floatValue() >= 0) {
            saldo.setValor(novoValor);

            Transferencia transferencia = new Transferencia(cpf, valor);
            transferenciaRepository.save(transferencia);
        } else {
            throw new DataIntegrityViolationException(SALDO_INSUFICIENTE);
        }
    }

    public Saldo getSaldo(String cpf){
        return repository.findByCpf(cpf);
    }
}
