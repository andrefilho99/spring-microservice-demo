package com.andrefilho99.ticketservice.repository;

import com.andrefilho99.ticketservice.domain.TicketOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketOrderRepository extends JpaRepository<TicketOrder, Long> {
}
