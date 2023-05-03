package com.andrefilho99.ticketservice.external.client;

import com.andrefilho99.ticketservice.external.decoder.PaymentMethodCustomErrorDecoder;
import com.andrefilho99.ticketservice.external.dto.PaymentMethodResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PAYMENT-SERVICE", path = "/payments", configuration = {PaymentMethodCustomErrorDecoder.class})
public interface PaymentMethodService {

    @GetMapping("/{id}")
    ResponseEntity<PaymentMethodResponse> findById(@PathVariable Long id);
}
