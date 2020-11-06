package com.movie.controller;

import com.movie.domain.MovieDto;
import com.movie.exceptions.NotFoundException;
import com.movie.mapper.MovieMapper;
import com.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService service;

    @Autowired
    private MovieMapper mapper;

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MovieDto>findAllMovies() {
        return mapper.mapToMovieDtoList(service.findAllMovies());
    }

    @GetMapping(path = "/{movieId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public MovieDto getMovie(@PathVariable Long movieId) throws NotFoundException {
        return mapper.mapToMovieDto(service.getMovieById(movieId).orElseThrow(NotFoundException::new));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createMovie(@RequestBody MovieDto movieDto) {
        service.saveMovie(mapper.mapToMovie(movieDto));
    }

    @DeleteMapping(path = "{movieId")
    public void deleteMovie(@PathVariable Long movieId){
        service.deleteMovie(movieId);
    }

    @PutMapping(path = "/{movieId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public MovieDto updateMovie(@PathVariable Long movieId, @RequestBody MovieDto movieDto) {
        return mapper.mapToMovieDto(service.saveMovie(mapper.mapToMovie(movieDto)));
    }

    @PatchMapping(path = "{movieId", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public MovieDto updateMovie( @RequestBody Map<String, String> updates, @PathVariable Long movieId) {
        return mapper.mapToMovieDto(service.updateMovie(updates, movieId));
    }
}

