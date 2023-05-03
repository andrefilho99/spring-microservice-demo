package com.andrefilho99.movieservice.service;

import com.andrefilho99.movieservice.domain.Movie;
import com.andrefilho99.movieservice.exceptions.MovieNotFoundException;
import com.andrefilho99.movieservice.exceptions.SpotsException;
import com.andrefilho99.movieservice.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findById(Long id) {
        log.info("Looking for movie with id: {}", id);
        return movieRepository.findById(id).orElseThrow(
                () -> new MovieNotFoundException(String.format("Movie with Id %d not found.", id))
        );
    }

    public Movie create(Movie movie) {
        log.info("Creating movie with title: ", movie.getTitle());
        return movieRepository.save(movie);
    }

    public void delete(Long id) {
        log.info("Deleting movie with id: {}", id);
        Movie movie = findById(id);
        movieRepository.delete(movie);
    }

    public void takeSpot(Long id, Integer spotAmount) {
        Movie movie = findById(id);

        if(spotAmount <= 0 || spotAmount > movie.getSpots()) {
            throw new SpotsException(
                    String.format("Movie have %d spots and you tried to take %d spots.", movie.getSpots(), spotAmount)
            );
        }

        log.info("Taking {} spots from movie with id: {}", spotAmount, id);
        movie.setSpots(movie.getSpots() - spotAmount);
        movieRepository.save(movie);
    }

    public void giveSpot(Long id, Integer spotAmount) {

        Movie movie = findById(id);

        if(spotAmount <= 0) {
            throw new SpotsException(
                    String.format("Cannot give {} spots back to movie with id: {} ", spotAmount, movie.getSpots())
            );
        }

        log.info("Giving {} spots back to movie with id: {}", spotAmount, id);
        movie.setSpots(movie.getSpots() + spotAmount);
        movieRepository.save(movie);
    }
}
