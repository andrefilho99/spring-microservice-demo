package com.andrefilho99.ticketservice.exceptions;

public class PaymentMethodLimitExceededException extends RuntimeException {
    public PaymentMethodLimitExceededException(String message) {
        super(message);
    }
}
