package com.andrefilho99.ticketservice.exceptions;

public class TicketOrderNotFoundException extends RuntimeException {
    public TicketOrderNotFoundException(String message) {
        super(message);
    }
}
