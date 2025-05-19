package es.upsa.dasi.web.Application.impl;

import Entities.Expediente;
import Exceptions.AppException;
import es.upsa.dasi.web.Application.AddExpedienteUseCase;
import es.upsa.dasi.web.Domain.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AddExpedienteUseCaseImpl implements AddExpedienteUseCase {

    @Inject
    Repository repository;

    @Override
    public Expediente addExpediente(Expediente expediente) throws AppException {
        return repository.addRepository(expediente);
    }
}
