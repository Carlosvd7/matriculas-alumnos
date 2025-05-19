package es.upsa.dasi.PracticaExtraordinaria.gateway.Application;

import Entities.Expediente;
import Exceptions.AppException;

public interface DeleteExpedienteUseCase {
    String deleteExpediente(String cod) throws AppException;
}
