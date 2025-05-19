package es.upsa.dasi.PracticaExtraordinaria.gateway.Application;

import Entities.Expediente;
import Exceptions.AppException;

public interface FindExpedienteByCodUseCase {
    Expediente findByCod(String cod) throws AppException;
}
