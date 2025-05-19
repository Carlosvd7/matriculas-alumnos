package es.upsa.dasi.PracticaExtraordinaria.expedientes.Application;

import Exceptions.AppException;

public interface DeleteExpedienteUseCase {
    String execute(String cod) throws AppException;
}
