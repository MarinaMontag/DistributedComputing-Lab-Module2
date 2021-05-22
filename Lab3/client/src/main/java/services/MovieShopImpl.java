package services;

import javafx.util.Pair;
import model.Genre;
import model.Movie;

import java.util.ArrayList;

public class MovieShopImpl implements MovieShop {
    @Override
    public void displayShop(Pair<ArrayList<Genre>, ArrayList<Movie>> info) {
        ArrayList<Genre>genres= info.getKey();
        ArrayList<Movie>movies= info.getValue();
        for(Genre genre: genres){
            System.out.println(genre);
            for(Movie movie: movies){
                if(movie.getGenre().equals(genre))
                    System.out.println(movie);
            }
        }
        System.out.println();
    }
}
