package com.andrefilho99.paymentservice.service;

import com.andrefilho99.paymentservice.domain.PaymentMethod;
import com.andrefilho99.paymentservice.exceptions.PaymentNotFoundException;
import com.andrefilho99.paymentservice.repository.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Service
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    public List<PaymentMethod> findAll() {
        return paymentMethodRepository.findAll();
    }

    public PaymentMethod findById(Long id) {
        log.info("Looking for payment method with id: {}", id);
        return paymentMethodRepository.findById(id).orElseThrow(
                () -> new PaymentNotFoundException(String.format("Payment with Id %d not found.", id))
        );
    }

    public PaymentMethod create(PaymentMethod paymentMethod) {
        log.info("Creating payment method with name: ", paymentMethod.getName());
        return paymentMethodRepository.save(paymentMethod);
    }

    public void delete(Long id) {
        log.info("Deleting payment method with id: {}", id);
        PaymentMethod paymentMethod = findById(id);
        paymentMethodRepository.delete(paymentMethod);
    }
}
