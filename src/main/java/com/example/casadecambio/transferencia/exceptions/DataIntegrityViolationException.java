package com.example.casadecambio.transferencia.exceptions;

public class DataIntegrityViolationException extends RuntimeException {

    public static final String CPF_JA_CADASTRADO = "O CPF informado já está cadastrado no sistema";
    public static final String CPF_NAO_ENCONTRADO = "O CPF informado ão foi encontrado";
    public static final String SALDO_INSUFICIENTE = "Saldo insuficiente para a transação";
    public static final String VALOR_INVALIDO = "O valor informado para transação é inferior a zero";


    public DataIntegrityViolationException(String msg) {
        super(msg);
    }
}
