package ru.nsu.ccfit.lopatkin.store.common.exception;

/**
 *  Высокоуровневое исключение, которое будет выводиться на UI
 */
public class LogicException extends RuntimeException {

    public LogicException(String message) {
        super(message);
    }

    public LogicException(String message, Throwable cause) {
        super(message, cause);
    }
}
