package com.example.casadecambio.transferencia.controller;

import com.example.casadecambio.transferencia.model.Saldo;
import com.example.casadecambio.transferencia.service.SaldoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/saldo")
public class SaldoController {

    @Autowired
    private SaldoService service;

    @PostMapping("/transferencia/{cpfOrigem}/{cpfDestino}/{valor}")
    @ApiOperation("Realiza a transferencia entre dois cpf e salva a operação")
    private ResponseEntity<String> transferencia(@RequestParam String cpfOrigem, @RequestParam String cpfDestino, @RequestParam BigDecimal valor) {
        return ResponseEntity.ok().body(service.transferencia(cpfOrigem, cpfDestino, valor));
    }

    @PostMapping("/atualizar/{cpfOrigem}/{valor}")
    @ApiOperation("Salva um saldo e realiza a transferencia para o mesmo cpf")
    private ResponseEntity<Saldo> atualizarSaldo(@RequestParam String cpfOrigem, @RequestParam BigDecimal valor) {
        return ResponseEntity.ok().body(service.update(cpfOrigem, valor));
    }

    @PostMapping("/create/{cpf}")
    @ApiOperation("Cria uma conta de investimento com saldo 0")
    private ResponseEntity<Saldo> create(@RequestParam String cpf) {
        return ResponseEntity.ok().body(service.create(cpf));
    }
}
