package es.upsa.dasi.web.Application.impl;

import Entities.Expediente;
import Exceptions.AppException;
import es.upsa.dasi.web.Application.FindExpedienteByDniUseCase;
import es.upsa.dasi.web.Domain.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FindExpedienteByDniUseCaseImpl implements FindExpedienteByDniUseCase {

    @Inject
    Repository repository;

    @Override
    public Expediente findExpedienteByDni(String dni) throws AppException {
        return repository.findExpedienteByDni(dni);
    }
}
