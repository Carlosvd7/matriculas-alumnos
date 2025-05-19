package es.upsa.dasi.web.Application.impl;

import Entities.Expediente;
import Exceptions.AppException;
import es.upsa.dasi.web.Application.FindExpedienteByCodUseCase;
import es.upsa.dasi.web.Domain.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FindExpedienteByCodUseCaseImpl implements FindExpedienteByCodUseCase {

    @Inject
    Repository repository;

    @Override
    public Expediente findExpedienteByCod(String cod) throws AppException {
        return repository.findExpedienteByCod(cod);
    }
}
