package com.example.casadecambio.transferencia.service;

import com.example.casadecambio.transferencia.exceptions.DataIntegrityViolationException;
import com.example.casadecambio.transferencia.model.Saldo;
import com.example.casadecambio.transferencia.model.SaldoBuilder;
import com.example.casadecambio.transferencia.repository.SaldoRepository;
import com.example.casadecambio.transferencia.repository.TransferenciaRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;

import static com.example.casadecambio.transferencia.exceptions.DataIntegrityViolationException.SALDO_INSUFICIENTE;
import static com.example.casadecambio.transferencia.exceptions.DataIntegrityViolationException.VALOR_INVALIDO;
import static com.example.casadecambio.transferencia.model.Transferencia.TRANSFERENCIA_REALIZADA;
import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class SaldoServiceTest {

    @InjectMocks
    private SaldoService service;

    @Mock
    private TransferenciaRepository transferenciaRepository;

    @Mock
    private SaldoRepository saldoRepository;

    @Before
    public void executeThis() {
        openMocks(this);
    }

    @Test
    public void shouldCreateAccount() {
        Saldo saldo = createSaldo("102.663.619-19", ZERO);
        when(saldoRepository.create(any(Saldo.class))).thenReturn(saldo);

        String cpf = saldo.getCpf();
        Saldo saveCLient = service.create(cpf);
        assertEquals(cpf, saveCLient.getCpf());
    }

    @Test
    public void shouldTransfer() {
        BigDecimal valorOrigem = valueOf(15834);
        BigDecimal valorDestino = valueOf(514);
        BigDecimal val = valueOf(1569);

        Saldo origem = createSaldo("102.663.619-19", valorOrigem);

        Saldo destino = createSaldo("617.287.489-68", valorDestino);
        when(saldoRepository.findByCpf("102.663.619-19")).thenReturn(origem);
        when(saldoRepository.findByCpf("617.287.489-68")).thenReturn(destino);

        String transferencia = service.transferencia(origem.getCpf(), destino.getCpf(), val);
        assertEquals(transferencia, TRANSFERENCIA_REALIZADA);
    }

    @Test
    public void shouldNotTransferWhenAmountIsNegative() {
        BigDecimal valorOrigem = valueOf(15834);
        BigDecimal valorDestino = valueOf(514);
        BigDecimal val = valueOf(-2589);

        Saldo origem = createSaldo("102.663.619-19", valorOrigem);

        Saldo destino = createSaldo("617.287.489-68", valorDestino);
        when(saldoRepository.findByCpf("102.663.619-19")).thenReturn(origem);
        when(saldoRepository.findByCpf("617.287.489-68")).thenReturn(destino);

        DataIntegrityViolationException exception = assertThrows(DataIntegrityViolationException.class,
                () -> service.transferencia(origem.getCpf(), destino.getCpf(), val));
        assertEquals(exception.getMessage(), VALOR_INVALIDO);
    }

    @Test
    public void shouldNotTransferWhenAmountIsGreaterThanBalance() {
        BigDecimal valorOrigem = ZERO;
        BigDecimal valorDestino = valueOf(514);
        BigDecimal val = valueOf(1569);

        Saldo origem = createSaldo("102.663.619-19", valorOrigem);

        Saldo destino = createSaldo("617.287.489-68", valorDestino);
        when(saldoRepository.findByCpf("102.663.619-19")).thenReturn(origem);
        when(saldoRepository.findByCpf("617.287.489-68")).thenReturn(destino);

        DataIntegrityViolationException exception = assertThrows(DataIntegrityViolationException.class,
                () -> service.transferencia(origem.getCpf(), destino.getCpf(), val));
        assertEquals(exception.getMessage(), SALDO_INSUFICIENTE);
    }

    private Saldo createSaldo(String cpf, BigDecimal valor) {
        return new SaldoBuilder()
                .setCpf(cpf)
                .setValor(valor)
                .createSaldo();
    }


}
