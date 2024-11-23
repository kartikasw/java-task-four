package com.kartikasw.traveller.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.kartikasw.traveller.model.GeneralErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<GeneralErrorResponse> handleBusinessException(BusinessException exception, WebRequest request) {
        GeneralErrorResponse errorResponse = errorResponse(
                exception,
                request,
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private GeneralErrorResponse errorResponse(RuntimeException exception, WebRequest webRequest, int httpStatus, String reasonPhrase) {
        return GeneralErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(httpStatus)
                .error(reasonPhrase)
                .message(exception.getMessage())
                .path(webRequest.getDescription(false))
                .build();
    }
}
