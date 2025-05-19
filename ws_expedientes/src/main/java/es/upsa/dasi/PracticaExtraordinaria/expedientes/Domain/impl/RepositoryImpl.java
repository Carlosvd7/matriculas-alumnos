package es.upsa.dasi.PracticaExtraordinaria.expedientes.Domain.impl;
import Entities.Expediente;
import Exceptions.AppException;
import es.upsa.dasi.PracticaExtraordinaria.expedientes.Adapters.output.Dao;
import es.upsa.dasi.PracticaExtraordinaria.expedientes.Domain.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class RepositoryImpl implements Repository {

    @Inject
    Dao dao;

    @Override
    public Expediente findExpedienteByCod(String cod) throws AppException {
        return dao.findExpedienteByCod(cod);
    }

    @Override
    public Expediente findExpedienteByDni(String dni) throws AppException {
        return dao.findExpedienteByDni(dni);
    }

    @Override
    public Expediente addExpediente(Expediente expediente) throws AppException {
        return dao.addExpediente(expediente);
    }

    @Override
    public Expediente updateExpediente(Expediente expediente) throws AppException {
        return dao.updateExpediente(expediente);
    }

    @Override
    public String deleteExpediente(String cod) throws AppException {
        return dao.deleteExpediente(cod);
    }
}
