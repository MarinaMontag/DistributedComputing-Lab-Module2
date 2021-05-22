package services;

import javafx.util.Pair;
import model.Genre;
import model.Movie;

import java.util.ArrayList;

public interface MovieShop {
    Pair<ArrayList<Genre>, ArrayList<Movie>> getShop();
}
