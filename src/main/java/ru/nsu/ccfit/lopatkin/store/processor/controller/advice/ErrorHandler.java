package ru.nsu.ccfit.lopatkin.store.processor.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.nsu.ccfit.lopatkin.store.common.exception.LogicException;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.exception.ErrorResponseDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.exception.ExceptionDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.exception.ViolationDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorResponseDTO onConstraintViolationException(ConstraintViolationException e) {
        ErrorResponseDTO error = new ErrorResponseDTO();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            error.getViolations().add(
                    new ViolationDTO(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorResponseDTO onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorResponseDTO error = new ErrorResponseDTO();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.getViolations().add(
                    new ViolationDTO(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return error;
    }

    @ExceptionHandler(LogicException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ExceptionDTO onMethodLogicException(LogicException e) {
        log.error("Logic Exception during method execution: " + e.getMessage(), e);
        return new ExceptionDTO(e.getMessage(), e.getStackTrace());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    ExceptionDTO onMethodUnexpectedException(Exception e) {
        log.error("Unexpected Exception during method execution: " + e.getMessage(), e);
        return new ExceptionDTO(e.getMessage(), e.getStackTrace());
    }
}
