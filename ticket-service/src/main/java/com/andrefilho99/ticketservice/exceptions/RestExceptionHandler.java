package com.andrefilho99.ticketservice.exceptions;

import com.andrefilho99.ticketservice.external.exceptions.MovieNotFoundException;
import com.andrefilho99.ticketservice.external.exceptions.SpotsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(TicketOrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleTicketOrderNotFoundException(HttpServletRequest request, TicketOrderNotFoundException ex) {
        return ErrorResponse
                .builder()
                .timestamp(new Date())
                .status(HttpStatus.NOT_FOUND.value())
                .error(ex.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(PaymentMethodException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handlePaymentMethodException(HttpServletRequest request, PaymentMethodException ex) {
        return ErrorResponse
                .builder()
                .timestamp(new Date())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(ex.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(IncorrectAmountException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleIncorrectAmountException(HttpServletRequest request, IncorrectAmountException ex) {
        return ErrorResponse
                .builder()
                .timestamp(new Date())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(ex.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(SpotsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleSpotsException(HttpServletRequest request, SpotsException ex) {
        return ErrorResponse
                .builder()
                .timestamp(new Date())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(ex.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(MovieNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleMovieNotFoundException(HttpServletRequest request, MovieNotFoundException ex) {
        return ErrorResponse
                .builder()
                .timestamp(new Date())
                .status(HttpStatus.NOT_FOUND.value())
                .error(ex.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(PaymentMethodLimitExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handlePaymentMethodLimitExceededException(HttpServletRequest request, PaymentMethodLimitExceededException ex) {
        return ErrorResponse
                .builder()
                .timestamp(new Date())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(ex.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(ServiceNotAvailableException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    public ErrorResponse handleServiceNotAvailableException(HttpServletRequest request, ServiceNotAvailableException ex) {
        return ErrorResponse
                .builder()
                .timestamp(new Date())
                .status(HttpStatus.SERVICE_UNAVAILABLE.value())
                .error(ex.getMessage())
                .path(request.getRequestURI())
                .build();
    }
}