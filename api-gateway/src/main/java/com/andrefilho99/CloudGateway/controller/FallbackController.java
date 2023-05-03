package com.andrefilho99.CloudGateway.controller;

import com.andrefilho99.CloudGateway.exceptions.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class FallbackController {

    @RequestMapping("/ticket-service-fallback")
    public ResponseEntity<ErrorResponse> ticketServiceFallback() {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(new Date())
                .path("/ticket-order/**")
                .status(HttpStatus.SERVICE_UNAVAILABLE.value())
                .error("Ticket service is down!")
                .build();
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponse);
    }

    @RequestMapping("/movie-service-fallback")
    public ResponseEntity<ErrorResponse> movieServiceFallback() {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(new Date())
                .path("/movies/**")
                .status(HttpStatus.SERVICE_UNAVAILABLE.value())
                .error("Movie service is down!")
                .build();
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponse);
    }

    @RequestMapping("/payment-service-fallback")
    public ResponseEntity<ErrorResponse> paymentServiceFallback() {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(new Date())
                .path("/payments/**")
                .status(HttpStatus.SERVICE_UNAVAILABLE.value())
                .error("Payment service is down!")
                .build();
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponse);
    }
}
