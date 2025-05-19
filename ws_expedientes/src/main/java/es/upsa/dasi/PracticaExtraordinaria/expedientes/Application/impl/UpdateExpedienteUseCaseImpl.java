package es.upsa.dasi.PracticaExtraordinaria.expedientes.Application.impl;

import Entities.Expediente;
import Exceptions.AppException;
import es.upsa.dasi.PracticaExtraordinaria.expedientes.Application.UpdateExpedienteUseCase;
import es.upsa.dasi.PracticaExtraordinaria.expedientes.Domain.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.resteasy.plugins.providers.ReaderProvider;

@ApplicationScoped
public class UpdateExpedienteUseCaseImpl implements UpdateExpedienteUseCase {

    @Inject
    Repository repository;

    @Override
    public Expediente execute(Expediente expediente) throws AppException {
        return repository.updateExpediente(expediente);
    }
}
