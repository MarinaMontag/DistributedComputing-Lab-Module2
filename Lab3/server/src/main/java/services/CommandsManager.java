package services;

import model.exceptions.GenreException;
import model.exceptions.MovieException;

import java.io.IOException;

public interface CommandsManager {
    void sendShopInfo() throws IOException;
    void insertMovie() throws IOException, ClassNotFoundException;
    void insertGenre() throws IOException, ClassNotFoundException;
    void deleteGenre() throws IOException;
    void deleteMovie() throws IOException;
    void editMovie() throws IOException;
    void sendAmountOfFilmsOCertainGenre() throws IOException;
    void sendMovie() throws IOException;
    void sendListOfFilms() throws IOException;
    void sendListOfGenres() throws IOException;
}
