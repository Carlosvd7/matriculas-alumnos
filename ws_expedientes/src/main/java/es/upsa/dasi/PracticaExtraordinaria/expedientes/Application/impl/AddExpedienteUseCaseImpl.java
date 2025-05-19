package es.upsa.dasi.PracticaExtraordinaria.expedientes.Application.impl;

import Entities.Expediente;
import Exceptions.AppException;
import es.upsa.dasi.PracticaExtraordinaria.expedientes.Application.AddExpedienteUseCase;
import es.upsa.dasi.PracticaExtraordinaria.expedientes.Domain.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AddExpedienteUseCaseImpl implements AddExpedienteUseCase {

    @Inject
    Repository repository;

    @Override
    public Expediente execute(Expediente expediente) throws AppException {
        return repository.addExpediente(expediente);
    }
}
