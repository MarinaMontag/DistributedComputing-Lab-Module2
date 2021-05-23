package services;

import model.Genre;
import model.Movie;

import java.util.ArrayList;

public interface MovieShop {
    void displayShop(ArrayList<Movie> movies);
    Movie createMovie();
    Genre createGenre();
    int getIdOfGenreToDelete();
    int getIdOfMovieToDelete();
    int getIdOfMovieToEdit();
    int getGenreId();
    String getNewMovieName();
    String getNewMovieDirector();
    String getNewMovieProducer();
    String getNewMovieScreenwriter();
    String getNewMovieCompany();
    int getNewMovieDuration();
    String getNewMovieCountry();
    int getNewMovieYear();
    String getMovieName();
    void displayMovie(Movie movie);
    void displayMovies(ArrayList<Movie> movies);
    void displayGenres(ArrayList<Genre> genres);
}

