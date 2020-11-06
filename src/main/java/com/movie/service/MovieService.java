package com.movie.service;


import com.movie.domain.Movie;
import com.movie.repository.MoviesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final Logger log = LoggerFactory.getLogger(MovieService.class);

    @Autowired
    private MoviesRepository repository;

    public List<Movie> findAllMovies() {
        log.debug("Request to get all movies");
        return repository.findAll();
    }

    public Optional<Movie> getMovieById(Long movieId) {
        log.debug("Request to get movie with id: {}", movieId);
        return repository.findById(movieId);
    }

    public Movie saveMovie(Movie movie) {
        log.debug("Request to add movie: {}", movie);
        repository.save(movie);

        return movie;
    }

    public void deleteMovie(Long movieId) {
        log.debug("Request to remove movie with id: {}", movieId);
        repository.deleteById(movieId);
    }

    public Movie updateMovie(Map<String, String> updates, Long movieId) {

       Movie updatedMovie = repository.findMovieById(movieId);

        List<String> keys = updates.entrySet().stream()
                .map(k -> k.getKey())
                .collect(Collectors.toList());
        List<String> values = updates.entrySet().stream()
                .map(k -> k.getValue())
                .collect(Collectors.toList());

        if (keys.get(0).equals("title")){
            String updatedTitle = values.get(0);
            updatedMovie.setTitle(updatedTitle);

        } else {
            String updatedAuthor = values.get(0);
            updatedMovie.setAuthor(updatedAuthor);
        }

        saveMovie(updatedMovie);
        return updatedMovie;
    }
}

//        movies.add(new Movie(1L, "Psy", "Pasikowski"));
//        movies.add(new Movie(2L, "Ptaki", "Hitchkok"));
//        movies.add(new Movie(3L, "Ogniem i Mieczem", "Hoffmann"));
//        movies.add(new Movie(4L, "Pulp Fiction", "Tarantino"));
//        movies.add(new Movie(5L, "Rejs", "Bareja"));
//        movies.add(new Movie(6L, "Wesele", "Wajda"));
//        movies.add(new Movie(7L, "Drogówka", "Smarzowski"));


//    public Movie updateMovie(Map<String, String> updates, Long movieId) {
//        Movie updatedMovie = findMovieById(movieId);
//
//        List<String> keys = updates.entrySet().stream()
//                .map(k -> k.getKey())
//                .collect(Collectors.toList());
//        List<String> values = updates.entrySet().stream()
//                .map(k -> k.getValue())
//                .collect(Collectors.toList());
//
//        if (keys.get(0).equals("title")){
//            String updatedTitle = values.get(0);
//            updatedMovie.setTitle(updatedTitle);
//
//        } else {
//            String updatedAuthor = values.get(0);
//            updatedMovie.setAuthor(updatedAuthor);
//        }
//        return updatedMovie;
//    }