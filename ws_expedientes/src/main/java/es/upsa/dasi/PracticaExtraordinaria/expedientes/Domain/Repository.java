package es.upsa.dasi.PracticaExtraordinaria.expedientes.Domain;

import Entities.Expediente;
import Exceptions.AppException;

public interface Repository {
    Expediente findExpedienteByCod(String cod) throws AppException;

    Expediente findExpedienteByDni(String dni) throws AppException;

    Expediente addExpediente(Expediente expediente) throws AppException;

    Expediente updateExpediente(Expediente expediente) throws AppException;

    String deleteExpediente(String cod) throws AppException;
}
