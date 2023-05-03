package com.andrefilho99.ticketservice.controller;

import com.andrefilho99.ticketservice.domain.TicketOrder;
import com.andrefilho99.ticketservice.dto.TicketOrderRequest;
import com.andrefilho99.ticketservice.dto.TicketOrderResponse;
import com.andrefilho99.ticketservice.service.TicketOrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ticket-orders")
public class TicketOrderController {

    private final TicketOrderService ticketOrderService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<TicketOrderResponse>> findAll() {
        List<TicketOrder> ticketOrders = ticketOrderService.findAll();
        List<TicketOrderResponse> ticketOrderResponseList = ticketOrders
                .stream()
                .map(ticketOrder -> modelMapper.map(ticketOrder, TicketOrderResponse.class))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(ticketOrderResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketOrderResponse> findById(@PathVariable Long id) {
        TicketOrder ticketOrder = ticketOrderService.findById(id);
        TicketOrderResponse ticketOrderResponse = modelMapper.map(ticketOrder, TicketOrderResponse.class);

        return ResponseEntity.status(HttpStatus.OK).body(ticketOrderResponse);
    }

    @PostMapping
    public ResponseEntity<TicketOrderResponse> create(@RequestBody TicketOrderRequest ticketOrderRequest) {
        TicketOrder ticketOrder = ticketOrderService.create(modelMapper.map(ticketOrderRequest, TicketOrder.class));
        TicketOrderResponse ticketOrderResponse = modelMapper.map(ticketOrder, TicketOrderResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(ticketOrderResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        ticketOrderService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
