package es.upsa.dasi.PracticaExtraordinaria.expedientes.Application.impl;

import Entities.Expediente;
import Exceptions.AppException;
import es.upsa.dasi.PracticaExtraordinaria.expedientes.Application.FindExpedienteByCodUseCase;
import es.upsa.dasi.PracticaExtraordinaria.expedientes.Domain.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FindExpedienteByCodUseCaseImpl implements FindExpedienteByCodUseCase {

    @Inject
    Repository repository;


    @Override
    public Expediente execute(String cod) throws AppException {
        return repository.findExpedienteByCod(cod);
    }
}
