package es.upsa.dasi.PracticaExtraordinaria.gateway.Application.impl;

import Entities.Expediente;
import Exceptions.AppException;
import es.upsa.dasi.PracticaExtraordinaria.gateway.Application.DeleteExpedienteUseCase;
import es.upsa.dasi.PracticaExtraordinaria.gateway.Domain.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeleteExpedienteUseCaseImpl implements DeleteExpedienteUseCase {

    @Inject
    Repository repository;

    @Override
    public String deleteExpediente(String cod) throws AppException {
        return repository.deleteExpediente(cod);
    }
}
