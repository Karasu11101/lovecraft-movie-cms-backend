package it.jdk.openlab.lovecraftmoviemanagementsql.service;

import it.jdk.openlab.lovecraftmoviemanagementsql.$exception.SuperException;
import it.jdk.openlab.lovecraftmoviemanagementsql.entities.MovieEntity;
import it.jdk.openlab.lovecraftmoviemanagementsql.models.Movie;
import it.jdk.openlab.lovecraftmoviemanagementsql.repository.movie.MovieSpringDataRepository;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.movie.group.MovieValidationGroup.CreateValidationGroup;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.movie.group.MovieValidationGroup.DeleteValidationGroup;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.movie.group.MovieValidationGroup.UpdateValidationGroup;
import it.jdk.openlab.lovecraftmoviemanagementsql.validation.movie.model.MovieValidationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MovieService {

    private final MovieSpringDataRepository movieRepository;
    private final MovieValidationModel movieValidation;

    @Autowired
    public MovieService(MovieSpringDataRepository movieRepository, MovieValidationModel movieValidation) {
        this.movieRepository = movieRepository;
        this.movieValidation = movieValidation;
    }

    @Transactional(readOnly = false)
    public Movie insertMovie(Movie movie) throws SuperException {
        this.movieValidation.validate(movie, "One or more arguments are invalid", CreateValidationGroup.class);

        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setTitle(movie.getTitle());
        movieEntity.setDescription(movie.getDescription());
        movieEntity.setImage(movie.getImage());

        this.movieRepository.save(movieEntity);

        movie.setId(movieEntity.getId());
        movie.setVersion(0);

        return movie;
    }

    @Transactional(readOnly = false)
    public Movie updateMovie(Movie movie) throws SuperException {
        this.movieValidation.validate(movie, "One or more arguments are invalid", UpdateValidationGroup.class);

        Optional<MovieEntity> movieEntity = this.movieRepository.findById(movie.getId());

        MovieEntity movieEntityObj = movieEntity.orElseThrow(
                () -> new RuntimeException("Movie with id " + movie.getId() + " not found"));
        if(movieEntityObj.getVersion() != movie.getVersion()) {
            throw new OptimisticLockingFailureException("Entity version and DTO version do not match");
        }
        movieEntityObj.setTitle(movie.getTitle());
        movieEntityObj.setDescription(movie.getDescription());
        movieEntityObj.setImage(movie.getImage());

        movie.setVersion(movie.getVersion() + 1);

        return movie;
    }

    @Transactional(readOnly = false)
    public void deleteMovie(Movie movie) throws SuperException {
        this.movieValidation.validate(movie, "A movie with id " + movie.getId() + " was not found", DeleteValidationGroup.class);

        Optional<MovieEntity> movieEntity = this.movieRepository.findById(movie.getId());

        MovieEntity movieEntityObj = movieEntity.orElseThrow(
                () -> new RuntimeException("Movie with id " + movie.getId() + " not found"));
        if(movieEntityObj.getId() != movie.getId()) {
            throw new OptimisticLockingFailureException("Entity version and DTO version do not match");
        }
        this.movieRepository.delete(movieEntityObj);
    }

    public List<Movie> findAllMovies() {
        List<MovieEntity> movieEntities = this.movieRepository.findAll();
        List<Movie> movies = new ArrayList<>();

        for(MovieEntity movieEntity : movieEntities) {
            movies.add(new Movie(movieEntity.getId(), movieEntity.getTitle(), movieEntity.getDescription(),
                    movieEntity.getImage(), movieEntity.getVersion()));
        }

        return movies;
    }

//    public PageModel<Movie> findAllMovies(int page, int numOfElements) {
//        Page<MovieEntity> entityPage = movieRepository.findAll(PageRequest.of(page -1, numOfElements));
//        PageModel<Movie> moviePage = new PageModel<>();
//        moviePage.setTotalPages(entityPage.getTotalPages());
//        moviePage.setPage(entityPage.getNumber() + 1);
//        moviePage.setPageSize(entityPage.getSize());
//
//        List<Movie> movies = new ArrayList<>();
//
//        for(MovieEntity entity : entityPage.getContent()) {
//            movies.add(new Movie(entity.getId(), entity.getTitle(),
//                    entity.getDescription(), entity.getImage(), entity.getVersion()));
//        }
//
//        moviePage.setElements(movies);
//        return moviePage;
//    }

    public Optional<Movie> findMovieById(Integer id) {
        Optional<MovieEntity> movieEntity = this.movieRepository.findById(id);
        MovieEntity movieEntityObj = movieEntity.orElseThrow(
                () -> new RuntimeException("Movie with id " + id + " was not found"));
        return Optional.of(new Movie(movieEntityObj.getId(), movieEntityObj.getTitle(),
                movieEntityObj.getDescription(), movieEntityObj.getImage(), movieEntityObj.getVersion()));
    }

}
