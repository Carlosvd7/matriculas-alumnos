package es.upsa.dasi.web.Application.impl;

import Exceptions.AppException;
import es.upsa.dasi.web.Application.DeleteExpedienteUseCase;
import es.upsa.dasi.web.Domain.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;

import java.lang.annotation.Repeatable;

@ApplicationScoped
public class DeleteExpedienteUseCaseImpl implements DeleteExpedienteUseCase {
    @Inject
    Repository repository;

    @Override
    public String deleteExpediente(String cod) throws AppException {
        return repository.deleteExpediente(cod);
    }
}
