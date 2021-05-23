package services;

import model.Genre;
import model.Movie;

import java.util.ArrayList;
import java.util.Scanner;

public class MovieShopImpl implements MovieShop {
    private final Scanner scanner = new Scanner(System.in);
    @Override
    public void displayShop(ArrayList<Movie> movies) {
        if(movies==null){
            System.out.println("Bad request or something wrong with connection to database");
            System.out.println();
            return;
        }
        ArrayList<Genre> genres=getGenres(movies);
        for(Genre genre: genres){
            System.out.println(genre);
            for(Movie movie: movies){
                if(movie.getGenre().equals(genre))
                    System.out.println(movie);
            }
        }
        System.out.println();
    }

    @Override
    public Movie createMovie() {
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
        scanner.nextLine();
        System.out.println();
        return movie;
    }

    @Override
    public Genre createGenre() {
        Genre genre = new Genre();
        System.out.print("Enter genre name: ");
        genre.setName(scanner.nextLine());
        return genre;
    }

    @Override
    public int getIdOfGenreToDelete() {
        System.out.print("Enter genre id: ");
        int id=scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    @Override
    public int getIdOfMovieToDelete() {
        System.out.print("Enter film id: ");
        int id=scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    @Override
    public int getIdOfMovieToEdit() {
        System.out.print("Enter the id of a film you want to edit: ");
        int id=scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    @Override
    public int getGenreId() {
        System.out.print("Enter genre id: ");
        int id=scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    @Override
    public String getNewMovieName() {
        System.out.print("Enter a new film title: ");
        return scanner.nextLine();
    }

    @Override
    public String getNewMovieDirector() {
        System.out.print("Enter a new film director: ");
        return scanner.nextLine();
    }

    @Override
    public String getNewMovieProducer() {
        System.out.print("Enter a new film producer: ");
        return scanner.nextLine();
    }

    @Override
    public String getNewMovieScreenwriter() {
        System.out.print("Enter a new film screenwriter: ");
        return scanner.nextLine();
    }

    @Override
    public String getNewMovieCompany() {
        System.out.print("Enter a new film company: ");
        return scanner.nextLine();
    }

    @Override
    public int getNewMovieDuration() {
        System.out.print("Enter a new film duration: ");
        int duration=scanner.nextInt();
        scanner.nextLine();
        return duration;
    }

    @Override
    public String getNewMovieCountry() {
        System.out.print("Enter a new film country: ");
        return scanner.nextLine();
    }

    @Override
    public int getNewMovieYear() {
        System.out.print("Enter a new film premier: ");
        int year=scanner.nextInt();
        scanner.nextLine();
        return year;
    }

    @Override
    public String getMovieName() {
        System.out.print("Enter the film name: ");
        return scanner.nextLine();
    }

    @Override
    public void displayMovie(Movie movie) {
        if(movie==null)
            System.out.println("No movie named in this way");
        else System.out.println(movie);
        System.out.println();
    }

    @Override
    public void displayMovies(ArrayList<Movie> movies) {
        if(movies.size()==0)
            System.out.println("No movies");
        else{
            for (Movie movie: movies)
                System.out.println(movie);
        }
        System.out.println();
    }

    @Override
    public void displayGenres(ArrayList<Genre> genres) {
        if(genres.size()==0)
            System.out.println("No genres");
        else{
            for(Genre genre: genres)
                System.out.println(genre);
        }
        System.out.println();
    }

    private ArrayList<Genre> getGenres(ArrayList<Movie> movies){
        ArrayList<Genre>genres=new ArrayList<>();
        for(Movie movie: movies){
            if(!genres.contains(movie.getGenre()))
                genres.add(movie.getGenre());
        }
        return genres;
    }
}
