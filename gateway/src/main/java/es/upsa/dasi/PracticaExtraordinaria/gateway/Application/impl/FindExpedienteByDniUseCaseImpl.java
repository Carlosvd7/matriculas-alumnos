package es.upsa.dasi.PracticaExtraordinaria.gateway.Application.impl;

import Entities.Expediente;
import Exceptions.AppException;
import es.upsa.dasi.PracticaExtraordinaria.gateway.Application.FindExpedienteByDniUseCase;
import es.upsa.dasi.PracticaExtraordinaria.gateway.Domain.Repository;
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
