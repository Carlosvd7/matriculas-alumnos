package es.upsa.dasi.PracticaExtraordinaria.expedientes.Application.impl;

import Entities.Expediente;
import Exceptions.AppException;
import es.upsa.dasi.PracticaExtraordinaria.expedientes.Application.FindExpedienteByDniUseCase;
import es.upsa.dasi.PracticaExtraordinaria.expedientes.Domain.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FindExpedienteByDniUseCaseImpl implements FindExpedienteByDniUseCase {

    @Inject
    Repository repository;

    @Override
    public Expediente execute(String dni) throws AppException {
        return repository.findExpedienteByDni(dni);
    }
}
