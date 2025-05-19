package es.upsa.dasi.PracticaExtraordinaria.gateway.Application;

import Entities.Expediente;
import Exceptions.AppException;

public interface AddExpedienteUseCase {
    Expediente addExpediente(Expediente expediente) throws AppException;
}
