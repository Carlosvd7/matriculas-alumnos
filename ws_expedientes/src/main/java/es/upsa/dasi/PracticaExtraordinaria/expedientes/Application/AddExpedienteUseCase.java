package es.upsa.dasi.PracticaExtraordinaria.expedientes.Application;

import Entities.Expediente;
import Exceptions.AppException;

public interface AddExpedienteUseCase {
    Expediente execute(Expediente expediente) throws AppException;
}
