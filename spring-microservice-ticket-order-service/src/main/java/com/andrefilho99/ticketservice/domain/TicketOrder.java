package com.andrefilho99.ticketservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
public class TicketOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ticket_order_id")
    private Long id;

    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "payment_id")
    private Long paymentId;

    private Integer tickets;
    private Double total;
}
