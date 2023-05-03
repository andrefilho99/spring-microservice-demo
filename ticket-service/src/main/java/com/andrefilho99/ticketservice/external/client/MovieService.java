package com.andrefilho99.ticketservice.external.client;

import com.andrefilho99.ticketservice.external.decoder.MovieCustomErrorDecoder;
import com.andrefilho99.ticketservice.external.dto.MovieResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "MOVIE-SERVICE", path = "/movies", configuration = {MovieCustomErrorDecoder.class})
public interface MovieService {

    @GetMapping("/{id}")
    ResponseEntity<MovieResponse> findById(@PathVariable Long id);

    @PutMapping("/{id}/take-spots")
    ResponseEntity takeSpots(@PathVariable Long id, @RequestParam Integer spots);

    @PutMapping("/{id}/give-spots")
    ResponseEntity giveSpots(@PathVariable Long id, @RequestParam Integer spots);
}
