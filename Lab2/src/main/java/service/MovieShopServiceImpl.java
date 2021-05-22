package service;

import dao.GenreDAO;
import dao.MovieDAO;
import exceptions.GenreException;
import exceptions.MovieException;
import javafx.util.Pair;
import model.Genre;
import model.Movie;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class MovieShopServiceImpl implements MovieShopService{
    Scanner scanner = new Scanner(System.in);
    @Override
    public void insertMovie(){
        Movie movie=new Movie();
        Genre genre = new Genre();
        System.out.print("Enter film title: ");
        movie.setName(scanner.nextLine());
        System.out.print("Enter film director: ");
        movie.setDirector(scanner.nextLine());
        System.out.print("Enter film producer: ");
        movie.setProducer(scanner.nextLine());
        System.out.print("Enter film screenwriter: ");
        movie.setScreenwriter(scanner.nextLine());
        System.out.print("Enter film company: ");
        movie.setCompany(scanner.nextLine());
        System.out.print("Enter film country: ");
        movie.setCountry(scanner.nextLine());
        System.out.print("Enter film duration: ");
        movie.setDuration(scanner.nextInt());
        System.out.print("Enter the year of film premier: ");
        movie.setYear(scanner.nextInt());
        System.out.print("Enter genre id: ");
        genre.setCode(scanner.nextInt());
        movie.setGenre(genre);
        MovieDAO.insert(movie);
        scanner.nextLine();
        System.out.println();
    }

    @Override
    public void insertGenre() throws GenreException {
        Genre genre=new Genre();
        System.out.print("Enter genre name: ");
        genre.setName(scanner.nextLine());
        GenreDAO.insert(genre);
        System.out.println();
    }

    @Override
    public void updateMovie(int id) throws MovieException {
        int command=0;
        boolean next = true;
        while(next){
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
            System.out.print("Enter answer: ");
            command= scanner.nextInt();
            scanner.nextLine();
            switch (command){
                case 1:
                    System.out.print("Enter a new film title: ");
                    MovieDAO.updateName(id, scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Enter a new film director: ");
                    MovieDAO.updateDirector(id, scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Enter a new film producer: ");
                    MovieDAO.updateProducer(id, scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Enter a new film screenwriter: ");
                    MovieDAO.updateScreenwriter(id, scanner.nextLine());
                    break;
                case 5:
                    System.out.print("Enter a new production company: ");
                    MovieDAO.updateCompany(id, scanner.nextLine());
                    break;
                case 6:
                    System.out.print("Enter duration of the film in minutes: ");
                    MovieDAO.updateDuration(id, scanner.nextInt());
                    scanner.nextLine();
                    break;
                case 7:
                    System.out.print("Enter the country: ");
                    MovieDAO.updateCountry(id, scanner.nextLine());
                    break;
                case 8:
                    System.out.print("Enter the year of the film premier: ");
                    MovieDAO.updateYear(id, scanner.nextInt());
                    scanner.nextLine();
                    break;
                default:
                    next=false;
            }
            System.out.println();
        }
    }

    @Override
    public void updateGenre(int id) throws GenreException {
        System.out.print("Enter a new genre name: ");
        GenreDAO.update(id, scanner.nextLine());
    }

    @Override
    public void deleteMovie(int id) throws MovieException {
        MovieDAO.deleteMovieById(id);
    }

    @Override
    public void deleteGenre(int id) throws GenreException{
        try {
            MovieDAO.deleteByGenreId(id);
        } catch (MovieException e){
            System.out.println(e.getMessage());
        }
        GenreDAO.deleteGenreById(id);
    }

    @Override
    public Genre getGenre(int id) throws GenreException {
        return GenreDAO.selectGenreById(id);
    }

    @Override
    public Movie getMovie(int id) throws MovieException {
        return MovieDAO.getMovie(id);
    }

    @Override
    public ArrayList<Genre> getAllGenres(){
        return GenreDAO.selectGenres();
    }

    @Override
    public ArrayList<Movie> getMoviesByGenreId(int id) throws MovieException {
        return MovieDAO.getMoviesByGenreId(id);
    }

    @Override
    public Pair getShop(){
        ArrayList<Movie>movies=MovieDAO.selectAll();
        ArrayList<Genre>genres=GenreDAO.selectGenres();
        Queue<Genre>genreQueue=new ArrayDeque<>(genres);
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
