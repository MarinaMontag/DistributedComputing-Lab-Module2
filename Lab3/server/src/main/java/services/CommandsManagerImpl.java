package services;

import model.Genre;
import model.Movie;
import model.exceptions.GenreException;
import model.exceptions.MovieException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class CommandsManagerImpl implements CommandsManager{
    private final MovieShop service;
    private final ObjectOutputStream objectOutputStream;
    private final ObjectInputStream objectInputStream;

    private final int COMMAND_TO_EDIT_TITLE = 1;
    private final int COMMAND_TO_EDIT_DIRECTOR = 2;
    private final int COMMAND_TO_EDIT_PRODUCER = 3;
    private final int COMMAND_TO_EDIT_SCREENWRITER = 4;
    private final int COMMAND_TO_EDIT_COMPANY = 5;
    private final int COMMAND_TO_EDIT_DURATION = 6;
    private final int COMMAND_TO_EDIT_COUNTRY = 7;
    private final int COMMAND_TO_EDIT_YEAR = 8;

    public CommandsManagerImpl(MovieShop service, ObjectOutputStream objectOutputStream,
                               ObjectInputStream objectInputStream) {
        this.service = service;
        this.objectOutputStream = objectOutputStream;
        this.objectInputStream = objectInputStream;
    }

    @Override
    public void sendShopInfo() throws IOException {
        sendObject(service.getShop());
    }

    @Override
    public void insertMovie() throws IOException, ClassNotFoundException{
            Movie movie=(Movie)objectInputStream.readObject();
            sendString(service.insertMovie(movie));
    }

    @Override
    public void insertGenre() throws IOException, ClassNotFoundException{
            Genre genre=(Genre) objectInputStream.readObject();
            sendString(service.insertGenre(genre));
    }

    @Override
    public void deleteGenre() throws IOException{
            int genreId=objectInputStream.readInt();
            sendString(service.deleteGenre(genreId));
    }

    @Override
    public void deleteMovie() throws IOException{
            int movieId=objectInputStream.readInt();
            sendString(service.deleteMovie(movieId));
    }

    @Override
    public void editMovie() throws IOException {
            int movieId=objectInputStream.readInt();
            boolean next=true;
            int command=-1;
            while (next){
                command=objectInputStream.readInt();
                switch (command) {
                    case COMMAND_TO_EDIT_TITLE -> {
                        String name = objectInputStream.readUTF();
                        sendString(service.updateMovieTitle(movieId, name));
                    }
                    case COMMAND_TO_EDIT_DIRECTOR -> {
                        String director = objectInputStream.readUTF();
                        sendString(service.updateMovieDirector(movieId, director));
                    }
                    case COMMAND_TO_EDIT_PRODUCER -> {
                        String producer = objectInputStream.readUTF();
                        sendString(service.updateMovieProducer(movieId, producer));
                    }
                    case COMMAND_TO_EDIT_SCREENWRITER -> {
                        String screenwriter = objectInputStream.readUTF();
                        sendString(service.updateMovieScreenwriter(movieId, screenwriter));
                    }
                    case COMMAND_TO_EDIT_COMPANY -> {
                        String company = objectInputStream.readUTF();
                        sendString(service.updateMovieCompany(movieId, company));
                    }
                    case COMMAND_TO_EDIT_DURATION -> {
                        int duration = objectInputStream.readInt();
                        sendString(service.updateMovieDuration(movieId, duration));
                    }
                    case COMMAND_TO_EDIT_COUNTRY -> {
                        String country = objectInputStream.readUTF();
                        sendString(service.updateMovieCountry(movieId, country));
                    }
                    case COMMAND_TO_EDIT_YEAR -> {
                        int year = objectInputStream.readInt();
                        sendString(service.updateMovieYear(movieId, year));
                    }
                    default -> next = false;
                }
            }
    }

    @Override
    public void sendAmountOfFilmsOCertainGenre() throws IOException {
        int genreId=objectInputStream.readInt();
        sendInteger(service.getAmountOfFilmsByGenreId(genreId));
    }

    @Override
    public void sendMovie() throws IOException {
        String name=objectInputStream.readUTF();
        sendObject(service.getMovie(name));
    }

    @Override
    public void sendListOfFilms() throws IOException {
        int genreId=objectInputStream.readInt();
        sendObject(service.getMoviesByGenreId(genreId));
    }

    @Override
    public void sendListOfGenres() throws IOException {
        sendObject(service.getAllGenres());
    }

    private void sendObject(Object object) throws IOException {
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
    }
    private void sendInteger(int command) throws IOException {
        objectOutputStream.writeInt(command);
        objectOutputStream.flush();
    }
    private void sendString(String str) throws IOException {
        objectOutputStream.writeUTF(str);
        objectOutputStream.flush();
    }
}
