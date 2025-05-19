package es.upsa.dasi.web.Application;

import Entities.Expediente;
import Exceptions.AppException;

public interface AddExpedienteUseCase {
    Expediente addExpediente(Expediente expediente) throws AppException;
}
