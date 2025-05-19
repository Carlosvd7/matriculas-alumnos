package es.upsa.dasi.web.Application;

import Entities.Expediente;
import Exceptions.AppException;

public interface FindExpedienteByDniUseCase {
    Expediente findExpedienteByDni(String dni) throws AppException;
}
