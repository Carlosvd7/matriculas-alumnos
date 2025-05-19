package es.upsa.dasi.PracticaExtraordinaria.gateway.Application;

import Entities.Expediente;
import Exceptions.AppException;

public interface UpdateExpedienteUseCase {
    Expediente updateExpediente(Expediente expediente) throws AppException;
}
