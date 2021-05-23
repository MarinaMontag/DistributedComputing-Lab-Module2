package services;

import dao.GenreDAO;
import dao.MovieDAO;
import model.Genre;
import model.Movie;
import model.exceptions.GenreException;
import model.exceptions.MovieException;

import java.util.ArrayList;

public class MovieShopImpl implements MovieShop{
    @Override
    public ArrayList<Movie> getShop(){
        ArrayList<Movie>movies= MovieDAO.selectAll();
        if(movies==null)
            return null;
        return movies;
    }

    @Override
    public String insertMovie(Movie movie){
        try {
            MovieDAO.insert(movie);
        } catch (MovieException e) {
            return e.getMessage();
        }
        return "Insertion of movie completed successfully!";
    }

    @Override
    public String insertGenre(Genre genre){
        try {
            GenreDAO.insert(genre);
        } catch (GenreException e) {
            return e.getMessage();
        }
        return "Insertion of genre completed successfully!";
    }

    @Override
    public String updateMovieTitle(int id, String name){
        try {
            MovieDAO.updateName(id, name);
        } catch (MovieException e) {
            return e.getMessage();
        }
        return "Updating movie name completed successfully!";
    }

    @Override
    public String updateMovieDirector(int id, String director){
        try {
            MovieDAO.updateDirector(id, director);
        } catch (MovieException e) {
            return e.getMessage();
        }
        return "Updating movie director completed successfully!";
    }

    @Override
    public String updateMovieProducer(int id, String producer){
        try {
            MovieDAO.updateProducer(id, producer);
        } catch (MovieException e) {
            return e.getMessage();
        }
        return "Updating movie producer completed successfully!";
    }

    @Override
    public String updateMovieScreenwriter(int id, String screenwriter){
        try {
            MovieDAO.updateScreenwriter(id, screenwriter);
        } catch (MovieException e) {
            return e.getMessage();
        }
        return "Updating movie screenwriter completed successfully!";
    }

    @Override
    public String updateMovieCompany(int id, String company) {
        try {
            MovieDAO.updateCompany(id, company);
        } catch (MovieException e) {
            return e.getMessage();
        }
        return "Updating movie company completed successfully!";
    }

    @Override
    public String updateMovieDuration(int id, int duration){
        try {
            MovieDAO.updateDuration(id, duration);
        } catch (MovieException e) {
            return e.getMessage();
        }
        return "Updating movie duration completed successfully!";
    }

    @Override
    public String updateMovieCountry(int id, String country){
        try {
            MovieDAO.updateCountry(id, country);
        } catch (MovieException e) {
            return e.getMessage();
        }
        return "Updating movie country completed successfully!";
    }

    @Override
    public String updateMovieYear(int id, int year){
        try {
            MovieDAO.updateYear(id, year);
        } catch (MovieException e) {
            return e.getMessage();
        }
        return "Updating movie year completed successfully!";
    }

    @Override
    public String deleteMovie(int id){
        try {
            MovieDAO.deleteMovieById(id);
        } catch (MovieException e) {
            return e.getMessage();
        }
        return "Operation of deleting movie completed successfully!";
    }

    @Override
    public String deleteGenre(int id) {
        try {
            MovieDAO.deleteByGenreId(id);
            GenreDAO.deleteGenreById(id);
        } catch (GenreException e) {
            return e.getMessage();
        }
        return "Operation of deleting genre with belonged films completed successfully!";
    }

    @Override
    public int getAmountOfFilmsByGenreId(int id) {
        try {
            return MovieDAO.getAmountOfMoviesWithGenreId(id);
        } catch (GenreException e) {
            return 0;
        }
    }

    @Override
    public Movie getMovie(String name) {
        try{
            return MovieDAO.getMovie(name);
        } catch (MovieException e) {
            return null;
        }
    }

    @Override
    public ArrayList<Genre> getAllGenres() {
        return GenreDAO.selectGenres();
    }

    @Override
    public ArrayList<Movie> getMoviesByGenreId(int id) {
        return MovieDAO.getMoviesByGenreId(id);
    }
}
