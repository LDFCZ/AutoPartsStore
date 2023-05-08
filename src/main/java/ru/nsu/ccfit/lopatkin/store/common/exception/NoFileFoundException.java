package ru.nsu.ccfit.lopatkin.store.common.exception;

public class NoFileFoundException extends RuntimeException {

    public NoFileFoundException(String message) {
        super(message);
    }

    public NoFileFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
