package es.upsa.dasi.web.Application.impl;

import Entities.Expediente;
import Exceptions.AppException;
import es.upsa.dasi.web.Application.UpdateExpedienteUseCase;
import es.upsa.dasi.web.Domain.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.lang.annotation.Repeatable;

@ApplicationScoped
public class UpdateExpedienteUseCaseImpl implements UpdateExpedienteUseCase {

    @Inject
    Repository repository;

    @Override
    public Expediente updateExpediente(Expediente expediente) throws AppException {
        return repository.updateExpediente(expediente);
    }
}
