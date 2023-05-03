package com.andrefilho99.paymentservice.controller;

import com.andrefilho99.paymentservice.domain.PaymentMethod;
import com.andrefilho99.paymentservice.dto.PaymentMethodRequest;
import com.andrefilho99.paymentservice.dto.PaymentMethodResponse;
import com.andrefilho99.paymentservice.service.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("payments")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<PaymentMethodResponse>> findAll() {
        List<PaymentMethod> paymentMethods = paymentMethodService.findAll();
        List<PaymentMethodResponse> paymentMethodResponseList = paymentMethods
                .stream()
                .map(paymentMethod -> modelMapper.map(paymentMethod, PaymentMethodResponse.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(paymentMethodResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethodResponse> findById(@PathVariable Long id) {
        PaymentMethod paymentMethod = paymentMethodService.findById(id);
        PaymentMethodResponse paymentMethodResponse = modelMapper.map(paymentMethod, PaymentMethodResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(paymentMethodResponse);
    }

    @PostMapping
    public ResponseEntity<PaymentMethodResponse> create(@RequestBody PaymentMethodRequest paymentMethodRequest) {
        PaymentMethod paymentMethod = paymentMethodService.create(modelMapper.map(paymentMethodRequest, PaymentMethod.class));
        PaymentMethodResponse paymentMethodResponse = modelMapper.map(paymentMethod, PaymentMethodResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentMethodResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        paymentMethodService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
