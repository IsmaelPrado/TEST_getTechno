package com.cristianprado.exception;

public class RecursoNoEncontradoException extends RuntimeException {

    public RecursoNoEncontradoException() {
        super();
    }

    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }

    public RecursoNoEncontradoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
