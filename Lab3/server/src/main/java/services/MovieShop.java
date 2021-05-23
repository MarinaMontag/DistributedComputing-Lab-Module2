package services;

import model.Genre;
import model.Movie;

import java.util.ArrayList;
public interface MovieShop {
    ArrayList<Movie> getShop();
    String insertMovie(Movie movie);
    String insertGenre(Genre genre);
    String updateMovieTitle(int id, String name);
    String updateMovieDirector(int id, String director);
    String updateMovieProducer(int id, String producer);
    String updateMovieScreenwriter(int id, String screenwriter);
    String updateMovieCompany(int id, String company);
    String updateMovieDuration(int id, int duration);
    String updateMovieCountry(int id, String country);
    String updateMovieYear(int id, int year);
    String deleteMovie(int id);
    String deleteGenre(int id);
    int getAmountOfFilmsByGenreId(int id);
//    Genre getGenre(int id) throws GenreException;
    Movie getMovie(String name);
    ArrayList<Genre>getAllGenres();
    ArrayList<Movie>getMoviesByGenreId(int id);
}
