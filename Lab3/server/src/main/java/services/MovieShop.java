package services;

import javafx.util.Pair;
import model.Genre;
import model.Movie;

import java.util.ArrayList;

public interface MovieShop {
    Pair<ArrayList<Genre>, ArrayList<Movie>> getShop();
    void insertMovie(Movie movie);
//    void insertGenre() throws GenreException;
//    void updateMovie(int id) throws MovieException;
//    void updateGenre(int id) throws GenreException;
//    void deleteMovie(int id) throws MovieException;
//    void deleteGenre(int id) throws GenreException;
//    Genre getGenre(int id) throws GenreException;
//    Movie getMovie(int id) throws MovieException;
//    ArrayList<Genre>getAllGenres();
//    ArrayList<Movie>getMoviesByGenreId(int id) throws MovieException;
}
