package it.jdk.openlab.lovecraftmoviemanagementsql.controller;

import it.jdk.openlab.lovecraftmoviemanagementsql.$exception.SuperException;
import it.jdk.openlab.lovecraftmoviemanagementsql.aspects.ToLog;
import it.jdk.openlab.lovecraftmoviemanagementsql.models.Movie;
import it.jdk.openlab.lovecraftmoviemanagementsql.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static it.jdk.openlab.lovecraftmoviemanagementsql.aspects.LoggingAspect.LOGGER_LEVEL;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @ToLog(level = LOGGER_LEVEL)
    @PostMapping(value = "/newMovie",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> insertMovie(@RequestBody Movie movie) throws SuperException {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.movieService.insertMovie(movie));
    }

    @ToLog(level = LOGGER_LEVEL)
    @PutMapping(value = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie) throws SuperException {
        return ResponseEntity.status(HttpStatus.OK).body(this.movieService.updateMovie(movie));
    }

    @ToLog(level = LOGGER_LEVEL)
    @DeleteMapping(value = "/delete",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> deleteMovie(@RequestBody Movie movie) throws SuperException {
        this.movieService.deleteMovie(movie);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ToLog(level = LOGGER_LEVEL)
    @GetMapping(value = "/list",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.status(HttpStatus.OK).body(this.movieService.findAllMovies());
    }

    @ToLog(level = LOGGER_LEVEL)
    @GetMapping(value = "/details/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> getMovieDetails(@PathVariable Integer id) {
        Optional<Movie> movie = this.movieService.findMovieById(id);
        if(movie.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(movie.get());
        }
    }
}
