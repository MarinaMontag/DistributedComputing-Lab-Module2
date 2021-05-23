package services;
import model.Genre;
import model.Movie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class CommandsManagerImpl implements CommandsManager{
    private final MovieShop service;
    private final ObjectOutputStream objectOutputStream;
    private final ObjectInputStream objectInputStream;
    private final Scanner scanner =new Scanner(System.in);
    private final int COMMAND_TO_DISPLAY_SHOP = 0;
    private final int COMMAND_TO_INSERT_GENRE = 1;
    private final int COMMAND_TO_INSERT_MOVIE = 2;
    private final int COMMAND_TO_DELETE_GENRE = 3;
    private final int COMMAND_TO_DELETE_MOVIE = 4;
    private final int COMMAND_TO_EDIT_MOVIE = 5;
    private final int COMMAND_TO_GET_AMOUNT_OF_FILMS = 6;
    private final int COMMAND_TO_GET_MOVIE = 7;
    private final int COMMAND_TO_GET_MOVIES = 8;
    private final int COMMAND_TO_GET_GENRES = 9;

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
    public void displayShop() throws IOException, ClassNotFoundException {
        sendInteger(COMMAND_TO_DISPLAY_SHOP);
        service.displayShop((ArrayList<Movie>)objectInputStream.readObject());
    }

    @Override
    public void insertMovie() throws IOException {
        sendInteger(COMMAND_TO_INSERT_MOVIE);
        Movie movie= service.createMovie();
        sendObject(movie);
        printResponse();
    }

    @Override
    public void insertGenre() throws IOException {
        sendInteger(COMMAND_TO_INSERT_GENRE);
        Genre genre= service.createGenre();
        sendObject(genre);
        printResponse();
    }

    @Override
    public void deleteGenre() throws IOException {
        sendInteger(COMMAND_TO_DELETE_GENRE);
        sendInteger(service.getIdOfGenreToDelete());
        printResponse();
    }

    @Override
    public void deleteMovie() throws IOException {
        sendInteger(COMMAND_TO_DELETE_MOVIE);
        sendInteger(service.getIdOfMovieToDelete());
        printResponse();
    }

    @Override
    public void editMovie() throws IOException {
        sendInteger(COMMAND_TO_EDIT_MOVIE);
        sendInteger(service.getIdOfMovieToEdit());
        boolean next=true;
        int command=-1;
        while (next){
            editMenu();
            command=getEditCommand();
            switch (command) {
                case 1 -> {
                    sendInteger(COMMAND_TO_EDIT_TITLE);
                    sendString(service.getNewMovieName());
                    printResponse();
                }
                case 2 -> {
                    sendInteger(COMMAND_TO_EDIT_DIRECTOR);
                    sendString(service.getNewMovieDirector());
                    printResponse();
                }
                case 3 -> {
                    sendInteger(COMMAND_TO_EDIT_PRODUCER);
                    sendString(service.getNewMovieProducer());
                    printResponse();
                }
                case 4 -> {
                    sendInteger(COMMAND_TO_EDIT_SCREENWRITER);
                    sendString(service.getNewMovieScreenwriter());
                    printResponse();
                }
                case 5 -> {
                    sendInteger(COMMAND_TO_EDIT_COMPANY);
                    sendString(service.getNewMovieCompany());
                    printResponse();
                }
                case 6 -> {
                    sendInteger(COMMAND_TO_EDIT_DURATION);
                    sendInteger(service.getNewMovieDuration());
                    printResponse();
                }
                case 7 -> {
                    sendInteger(COMMAND_TO_EDIT_COUNTRY);
                    sendString(service.getNewMovieCountry());
                    printResponse();
                }
                case 8 -> {
                    sendInteger(COMMAND_TO_EDIT_YEAR);
                    sendInteger(service.getNewMovieYear());
                    printResponse();
                }
                default -> {
                    sendInteger(command);
                    next = false;
                }
            }
        }
    }

    @Override
    public void showAmountOfFilmsOfCertainGenre() throws IOException {
        sendInteger(COMMAND_TO_GET_AMOUNT_OF_FILMS);
        sendInteger(service.getGenreId());
        System.out.println("Amount of films: "+objectInputStream.readInt());
    }

    @Override
    public void showMovie() throws IOException, ClassNotFoundException {
        sendInteger(COMMAND_TO_GET_MOVIE);
        sendString(service.getMovieName());
        service.displayMovie((Movie)objectInputStream.readObject());
    }

    @Override
    public void showListOfFilms() throws IOException, ClassNotFoundException {
        sendInteger(COMMAND_TO_GET_MOVIES);
        sendInteger(service.getGenreId());
        service.displayMovies((ArrayList<Movie>)objectInputStream.readObject());
    }

    @Override
    public void showListOfGenres() throws IOException, ClassNotFoundException {
        sendInteger(COMMAND_TO_GET_GENRES);
        service.displayGenres((ArrayList<Genre>)objectInputStream.readObject());
    }

    private void printResponse() throws IOException {
        String response=objectInputStream.readUTF();
        System.out.println(response);
        System.out.println();
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

    private int getEditCommand(){
        System.out.print("Enter answer: ");
        int command = scanner.nextInt();
        scanner.nextLine();
        return command;
    }

    private void editMenu(){
        System.out.println("Choose a characteristic you what to update:");
        System.out.println("1 -> Title");
        System.out.println("2 -> Director");
        System.out.println("3 -> Producer");
        System.out.println("4 -> Screenwriter");
        System.out.println("5 -> Film production company");
        System.out.println("6 -> Duration of a film");
        System.out.println("7 -> Country");
        System.out.println("8 -> Year of premier");
        System.out.println("9 -> COMPLETE");
    }
}
