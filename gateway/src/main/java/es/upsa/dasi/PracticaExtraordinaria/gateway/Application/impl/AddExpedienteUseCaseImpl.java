package es.upsa.dasi.PracticaExtraordinaria.gateway.Application.impl;

import Entities.Expediente;
import Exceptions.AppException;
import es.upsa.dasi.PracticaExtraordinaria.gateway.Application.AddExpedienteUseCase;
import es.upsa.dasi.PracticaExtraordinaria.gateway.Domain.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AddExpedienteUseCaseImpl implements AddExpedienteUseCase {

    @Inject
    Repository repository;

    @Override
    public Expediente addExpediente(Expediente expediente) throws AppException {
        return repository.addExpediente(expediente);
    }
}
