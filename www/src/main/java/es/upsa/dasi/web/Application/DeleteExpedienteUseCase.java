package es.upsa.dasi.web.Application;

import Exceptions.AppException;

public interface DeleteExpedienteUseCase {
    String deleteExpediente(String cod) throws AppException;
}
