package com.andrefilho99.ticketservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketOrderRequest {
    private Long id;
    private Long movieId;
    private Long paymentId;
    private Integer tickets;
    private Double total;
}
