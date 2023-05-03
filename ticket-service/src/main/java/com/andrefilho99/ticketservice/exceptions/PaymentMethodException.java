package com.andrefilho99.ticketservice.exceptions;

public class PaymentMethodException extends RuntimeException{
    public PaymentMethodException(String message) {
        super(message);
    }
}
