package es.upsa.dasi.PracticaExtraordinaria.expedientes.Application.impl;

import Exceptions.AppException;
import es.upsa.dasi.PracticaExtraordinaria.expedientes.Application.DeleteExpedienteUseCase;
import es.upsa.dasi.PracticaExtraordinaria.expedientes.Domain.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeleteExpedienteUseCaseImpl implements DeleteExpedienteUseCase {

    @Inject
    Repository repository;

    @Override
    public String execute(String cod) throws AppException {
        return repository.deleteExpediente(cod);
    }
}
