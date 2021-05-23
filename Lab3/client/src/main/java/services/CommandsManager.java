package services;

import java.io.IOException;

public interface CommandsManager {
    void displayShop() throws IOException, ClassNotFoundException;
    void insertMovie() throws IOException;
    void insertGenre() throws IOException;
    void deleteGenre() throws IOException;
    void deleteMovie() throws IOException;
    void editMovie() throws IOException;
    void showAmountOfFilmsOfCertainGenre() throws IOException;
    void showMovie() throws IOException, ClassNotFoundException;
    void showListOfFilms() throws IOException, ClassNotFoundException;
    void showListOfGenres() throws IOException, ClassNotFoundException;
}
