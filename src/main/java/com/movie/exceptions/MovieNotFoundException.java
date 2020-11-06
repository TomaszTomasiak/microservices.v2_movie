package com.movie.exceptions;

public class MovieNotFoundException extends Exception {
    public static String ERR_MOVIE_NOT_FOUND = "Cannot find movie";

    public MovieNotFoundException(String message) {
        super(message);
    }
}
