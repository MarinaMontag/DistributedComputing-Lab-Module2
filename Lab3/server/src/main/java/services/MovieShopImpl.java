package services;

import dao.GenreDAO;
import dao.MovieDAO;
import javafx.util.Pair;
import model.Genre;
import model.Movie;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class MovieShopImpl implements MovieShop{
    @Override
    public Pair<ArrayList<Genre>, ArrayList<Movie>> getShop() {
        ArrayList<Movie>movies= MovieDAO.selectAll();
        ArrayList<Genre>genres= GenreDAO.selectGenres();
        Queue<Genre> genreQueue=new ArrayDeque<>(genres);
        for(Movie movie: movies)
            for(Genre genre: genreQueue)
                if(movie.getGenre().getCode()==genre.getCode()){
                    movie.setGenre(genre);
                    genreQueue.remove(genre);
                    break;
                }
        return new Pair(genres, movies);
    }
}
