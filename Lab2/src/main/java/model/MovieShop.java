package model;

import exceptions.GenreException;
import exceptions.MovieException;
import javafx.util.Pair;
import service.MovieShopService;

import java.util.ArrayList;
import java.util.Scanner;

public final class MovieShop {
    private static MovieShop instance;
    private static MovieShopService service;
    private MovieShop(){}
    public static MovieShop getInstance(MovieShopService service){
        MovieShop.service = service;
        if(instance==null){
            instance = new MovieShop();
        }
        return instance;
    }
    public void work(){
        int command = 0;
        Boolean next = true;
        Scanner scanner = new Scanner(System.in);
        while (next) {
            try {
                displayShop();
                menu();
                command = scanner.nextInt();
                scanner.nextLine();
                next = executeCommand(command, scanner);
            } catch (MovieException | GenreException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void displayShop() throws MovieException, GenreException {
        Pair<ArrayList<Genre>, ArrayList<Movie>>pair=service.getShop();
        ArrayList<Genre>genres= pair.getKey();
        ArrayList<Movie>movies= pair.getValue();
        for(Genre genre: genres){
            System.out.println(genre);
            for(Movie movie: movies){
                if(movie.getGenre().equals(genre))
                    System.out.println(movie);
            }
        }
        System.out.println();
    }
    private void menu(){
        System.out.println("Choose operation: ");
        System.out.println("1 -> Add new genre");
        System.out.println("2 -> Add new movie");
        System.out.println("3 -> Delete genre");
        System.out.println("4 -> Delete movie");
        System.out.println("5 -> Edit genre");
        System.out.println("6 -> Edit movie characteristics");
        System.out.println("7 -> Search genre by id");
        System.out.println("8 -> Search movie by id");
        System.out.println("9 -> Show all genres");
        System.out.println("10 -> Show all movies by genre id");
        System.out.println("11 -> EXIT");
        System.out.println();
        System.out.print("Choose: ");
    }
    private boolean executeCommand(int command, Scanner scanner)
            throws GenreException, MovieException
    {
        switch(command){
            case 1:
                service.insertGenre();
                return true;
            case 2:
                service.insertMovie();
                return true;
            case 3:
                System.out.print("Enter genre id: ");
                service.deleteGenre(scanner.nextInt());
                scanner.nextLine();
                return true;
            case 4:
                System.out.print("Enter movie id: ");
                service.deleteMovie(scanner.nextInt());
                scanner.nextLine();
                return true;
            case 5:
                System.out.print("Enter genre id: ");
                service.updateGenre(scanner.nextInt());
                scanner.nextLine();
                return true;
            case 6:
                System.out.print("Enter movie id: ");
                service.updateMovie(scanner.nextInt());
                scanner.nextLine();
                return true;
            case 7:
                System.out.print("Enter genre id: ");
                System.out.println(service.getGenre(scanner.nextInt()));
                scanner.nextLine();
                System.out.println();
                return true;
            case 8:
                System.out.print("Enter movie id: ");
                System.out.println(service.getMovie(scanner.nextInt()));
                scanner.nextLine();
                System.out.println();
                return true;
            case 9:
                ArrayList<Genre> genres = service.getAllGenres();
                for (Genre genre: genres)
                    System.out.println(genre);
                System.out.println();
                return true;
            case 10:
                System.out.print("Enter genre id: ");
                ArrayList<Movie> movies = service.getMoviesByGenreId(scanner.nextInt());
                for (Movie movie: movies)
                    System.out.println(movie);
                System.out.println();
                return true;
            default:
                return false;
        }
    }
}
