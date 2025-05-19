package es.upsa.dasi.PracticaExtraordinaria.expedientes.Application;

import Entities.Expediente;
import Exceptions.AppException;

public interface FindExpedienteByDniUseCase {
    Expediente execute(String dni) throws AppException;
}
