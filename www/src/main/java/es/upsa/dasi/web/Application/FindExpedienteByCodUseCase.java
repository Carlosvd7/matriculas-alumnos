package es.upsa.dasi.web.Application;

import Entities.Expediente;
import Exceptions.AppException;

public interface FindExpedienteByCodUseCase {
    Expediente findExpedienteByCod(String cod) throws AppException;
}
