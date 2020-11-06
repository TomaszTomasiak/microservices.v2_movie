package com.movie.repository;


import com.movie.domain.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface MoviesRepository extends CrudRepository<Movie, Long> {


    @Override
    List<Movie> findAll();

    Movie findMovieById(Long id);

}
