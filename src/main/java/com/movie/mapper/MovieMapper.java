package com.movie.mapper;

import com.movie.domain.Movie;
import com.movie.domain.MovieDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieMapper {
    public Movie mapToMovie(final MovieDto movieDto) {
        return new Movie(
                movieDto.getId(),
                movieDto.getTitle(),
                movieDto.getAuthor());
    }

    public MovieDto mapToMovieDto(final Movie movie) {
        return new MovieDto(
                movie.getId(),
                movie.getTitle(),
                movie.getAuthor() );
    }

    public List<MovieDto> mapToMovieDtoList(final List<Movie> movieList){
        return movieList.stream()
                .map(m -> mapToMovieDto(m))
                .collect(Collectors.toList());
    }
}
