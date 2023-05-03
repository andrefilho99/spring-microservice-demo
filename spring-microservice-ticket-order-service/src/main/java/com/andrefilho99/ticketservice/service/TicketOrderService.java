package com.andrefilho99.ticketservice.service;

import com.andrefilho99.ticketservice.domain.TicketOrder;
import com.andrefilho99.ticketservice.exceptions.IncorrectAmountException;
import com.andrefilho99.ticketservice.exceptions.PaymentMethodLimitExceededException;
import com.andrefilho99.ticketservice.exceptions.TicketOrderNotFoundException;
import com.andrefilho99.ticketservice.external.client.MovieService;
import com.andrefilho99.ticketservice.external.client.PaymentMethodService;
import com.andrefilho99.ticketservice.external.dto.MovieResponse;
import com.andrefilho99.ticketservice.external.dto.PaymentMethodResponse;
import com.andrefilho99.ticketservice.repository.TicketOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Service
public class TicketOrderService {

    private final TicketOrderRepository ticketOrderRepository;
    private final MovieService movieService;
    private final PaymentMethodService paymentMethodService;

    public List<TicketOrder> findAll() {
        return ticketOrderRepository.findAll();
    }

    public TicketOrder findById(Long id) {
        log.info("Looking for ticket order with id: {}", id);
        return ticketOrderRepository.findById(id).orElseThrow(
                () -> new TicketOrderNotFoundException(String.format("Ticket order with Id %d not found.", id))
        );
    }

    public TicketOrder create(TicketOrder ticketOrder) {
        validatePaymentMethod(ticketOrder);
        validateAmount(ticketOrder);
        movieService.takeSpots(ticketOrder.getMovieId(), ticketOrder.getTickets());
        TicketOrder created = ticketOrderRepository.save(ticketOrder);
        log.info("Creating ticket order with id: ", created.getId());

        return created;
    }

    public void delete(Long id) {
        TicketOrder ticketOrder = findById(id);
        log.info("Deleting ticket order with id: {}", id);
        movieService.giveSpots(ticketOrder.getMovieId(), ticketOrder.getTickets());
        ticketOrderRepository.delete(ticketOrder);
    }

    private void validateAmount(TicketOrder ticketOrder) {
        MovieResponse movieResponse = movieService.findById(ticketOrder.getMovieId()).getBody();
        Double expectedTotal = ticketOrder.getTickets() * movieResponse.getPrice();
        if(expectedTotal.compareTo(ticketOrder.getTotal()) != 0)
            throw new IncorrectAmountException(String.format("Ticket order should have total of %.2f but it has %.2f.", (ticketOrder.getTickets() * movieResponse.getPrice()), ticketOrder.getTotal()));
    }


    private void validatePaymentMethod(TicketOrder ticketOrder) {
        PaymentMethodResponse paymentMethodResponse = paymentMethodService.findById(ticketOrder.getPaymentId()).getBody();
        if(paymentMethodResponse.getHasLimit()) {
            if(ticketOrder.getTotal() > paymentMethodResponse.getLimit())
                throw new PaymentMethodLimitExceededException(
                        String.format("Ticket order should have total of %.2f or less with payment method %s.",
                                paymentMethodResponse.getLimit(),
                                paymentMethodResponse.getName())
                );
        }
    }
}
