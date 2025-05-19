package es.upsa.dasi.PracticaExtraordinaria.gateway.Application.impl;

import Entities.Expediente;
import Exceptions.AppException;
import es.upsa.dasi.PracticaExtraordinaria.gateway.Application.UpdateExpedienteUseCase;
import es.upsa.dasi.PracticaExtraordinaria.gateway.Domain.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UpdateExpedienteUseCaseImpl implements UpdateExpedienteUseCase {

    @Inject
    Repository repository;

    @Override
    public Expediente updateExpediente(Expediente expediente) throws AppException {
        return repository.updateExpediente(expediente);
    }
}
