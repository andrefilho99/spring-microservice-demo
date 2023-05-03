package com.andrefilho99.movieservice.controller;

import com.andrefilho99.movieservice.domain.Movie;
import com.andrefilho99.movieservice.dto.MovieRequest;
import com.andrefilho99.movieservice.dto.MovieResponse;
import com.andrefilho99.movieservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<MovieResponse>> findAll() {
        List<Movie> movies = movieService.findAll();
        List<MovieResponse> movieResponseList = movies
                .stream()
                .map(movie -> modelMapper.map(movie, MovieResponse.class))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(movieResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> findById(@PathVariable Long id) {
        Movie movie = movieService.findById(id);
        MovieResponse movieResponse = modelMapper.map(movie, MovieResponse.class);

        return ResponseEntity.status(HttpStatus.OK).body(movieResponse);
    }

    @PostMapping
    public ResponseEntity<MovieResponse> create(@RequestBody MovieRequest movieRequest) {
        Movie movie = movieService.create(modelMapper.map(movieRequest, Movie.class));
        MovieResponse movieResponse = modelMapper.map(movie, MovieResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(movieResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        movieService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}/take-spots")
    public ResponseEntity takeSpots(@PathVariable Long id, @RequestParam Integer spots) {
        movieService.takeSpot(id, spots);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}/give-spots")
    public ResponseEntity giveSpots(@PathVariable Long id, @RequestParam Integer spots) {
        movieService.giveSpot(id, spots);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}