package com.andrefilho99.ticketservice.external.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodResponse {
    private Long id;
    private String name;
    private Boolean hasLimit;
    private Double limit;
}
