package com.andrefilho99.ticketservice.external.decoder;

import com.andrefilho99.ticketservice.exceptions.ErrorResponse;
import com.andrefilho99.ticketservice.exceptions.PaymentMethodException;
import com.andrefilho99.ticketservice.exceptions.PaymentMethodLimitExceededException;
import com.andrefilho99.ticketservice.exceptions.ServiceNotAvailableException;
import com.andrefilho99.ticketservice.external.exceptions.MovieNotFoundException;
import com.andrefilho99.ticketservice.external.exceptions.PaymentNotFoundException;
import com.andrefilho99.ticketservice.external.exceptions.SpotsException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;

@Log4j2
public class PaymentMethodCustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        log.info(response.status());
        log.info(response.body());

        if(response.status() == HttpStatus.SERVICE_UNAVAILABLE.value()) {
            throw new ServiceNotAvailableException("Payment service is down!");
        } else {

            ErrorResponse errorResponse = null;
            try {
                InputStream body = response.body().asInputStream();
                ObjectMapper objectMapper = new ObjectMapper();
                errorResponse = objectMapper.readValue(body, ErrorResponse.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            int statusCode = errorResponse.getStatus();
            String errorMessage = errorResponse.getError();

            switch (statusCode) {
                case 404:
                    return new PaymentMethodException(errorMessage);
                default:
                    return new RuntimeException("Internal server error.");
            }
        }
    }
}
