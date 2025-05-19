package es.upsa.dasi.PracticaExtraordinaria.expedientes.Application;

import Entities.Expediente;
import Exceptions.AppException;

public interface FindExpedienteByCodUseCase {
    Expediente execute(String cod) throws AppException;

}
