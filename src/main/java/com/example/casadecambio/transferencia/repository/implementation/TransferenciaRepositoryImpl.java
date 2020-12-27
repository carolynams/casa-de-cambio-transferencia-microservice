package com.example.casadecambio.transferencia.repository.implementation;

import com.example.casadecambio.transferencia.model.Transferencia;
import com.example.casadecambio.transferencia.repository.TransferenciaRepository;
import com.example.casadecambio.transferencia.repository.jpa.TransferenciaRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TransferenciaRepositoryImpl implements TransferenciaRepository {

    @Autowired
    private TransferenciaRepositoryJpa repositoryJpa;


    @Override
    public Transferencia save(Transferencia transferencia) {
        return repositoryJpa.save(transferencia);
    }
}
