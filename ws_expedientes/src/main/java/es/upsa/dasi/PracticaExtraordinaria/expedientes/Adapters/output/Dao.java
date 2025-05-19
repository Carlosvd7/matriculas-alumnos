package es.upsa.dasi.PracticaExtraordinaria.expedientes.Adapters.output;

import Entities.Expediente;
import Exceptions.AppException;

public interface Dao {
    Expediente findExpedienteByCod(String cod) throws AppException;

    Expediente findExpedienteByDni(String dni) throws AppException;

    Expediente addExpediente(Expediente expediente) throws AppException;

    Expediente updateExpediente(Expediente expediente) throws AppException;

    String deleteExpediente(String cod) throws AppException;
}
