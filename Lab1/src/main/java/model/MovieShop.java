package model;
import dom.DomParser;
import javafx.util.Pair;
import model.exeptions.GenreException;
import model.exeptions.MovieException;

import java.util.ArrayList;
import java.util.Scanner;

public final class MovieShop{
    private static MovieShop instance;
    private static String fileName;
    private ArrayList<Genre> genres;
    private ArrayList<Movie> movies;
    private static DomParser domParser=new DomParser(fileName);
    private MovieShop(){
        loadFromFile();
    }

    public String getFileName() {
        return fileName;
    }

    public static MovieShop getInstance(String fileName){
        MovieShop.fileName=fileName;
        domParser=new DomParser(fileName);
        if(instance==null){
            instance = new MovieShop();
        }
        return instance;
    }

    public void displayShop() throws MovieException, GenreException {
        if(genres==null){
            genres=new ArrayList<>();
            System.out.println("Empty");
        }
        for(Genre genre: genres){
            System.out.println(genre);
            for(Movie movie: getMoviesByGenreId(genre.getCode())){
                System.out.println(movie);
            }
        }
        System.out.println();
    }

    // Записать данные в файл XML
    public void saveToFile(String filename)
    {
        domParser.saveOrUpdate(genres, movies);
    }
    // Прочитать данные из файла XML
    private void loadFromFile()
    {
        Pair<ArrayList<Genre>,ArrayList<Movie>>pair= domParser.loadInfo();
        genres=pair.getKey();
        movies= pair.getValue();
    }

    public ArrayList<Genre> getGenres() {
        if(genres==null)
            genres=new ArrayList<>();
        return genres;
    }

    public ArrayList<Movie> getMoviesByGenreId(int code) throws MovieException, GenreException {
        if(movies==null){
            movies=new ArrayList<>();
            throw new MovieException("No movies exist yet");
        }
        if(genres==null) {
            genres=new ArrayList<>();
            throw new GenreException("No genres");
        }
        if(!containsGenre(code)){
            throw new GenreException("No such genre");
        }
        ArrayList<Movie> genreMovies=new ArrayList<>();
        for(Movie movie: movies)
            if(movie.getGenre().getCode()==code)
                genreMovies.add(movie);
        return genreMovies;
    }

    public void addGenre(Genre genre) throws GenreException {

        if(genres==null){
            genres=new ArrayList<>();
            genres.add(genre);
        }else{
            if(!genres.contains(genre))
                genres.add(genre);
            else throw new GenreException("Such genre already exists");
        }
    }
    public Genre getGenre(int code) throws GenreException {
        if(genres==null){
            genres=new ArrayList<>();
            throw new GenreException("No genres");
        }
        for (Genre genre: genres)
            if(genre.getCode()==code)
                return genre;
        throw new GenreException("No genre with such code");
    }

    public Movie getMovie(int code) throws MovieException {
        if(movies==null){
            movies=new ArrayList<>();
            throw new MovieException("No movies exist yet");
        }
        if(!containsMovie(code))
            throw new MovieException("No such movie");
        for(Movie movie: movies)
            if(movie.getCode()==code)
                return movie;
        return null;
    }

    public Genre getGenreInd(int index) throws GenreException {
        if(genres==null) {
            genres=new ArrayList<>();
            throw new GenreException("No genres");
        }
        if(index>=genres.size())
            throw new GenreException("Index is too great");
        return genres.get(index);
    }
    public int countGenres()
    {
        if(genres==null){
            genres=new ArrayList<>();
        }
        return genres.size();
    }
    public void deleteGenre(int code) throws GenreException {
        if(genres==null){
            genres=new ArrayList<>();
            throw new GenreException("No genre exists yet");
        }
        if(genres.removeIf(genre -> genre.getCode() == code)){
            movies.removeIf(movie -> movie.getGenre().getCode()==code);
        }
        else throw new GenreException("No genre with such code");
    }

    public void deleteMovie(int code) throws MovieException {
        if(movies==null){
            movies=new ArrayList<>();
            throw new MovieException("No movies exists yet");
        }
        if(!movies.removeIf(movie -> movie.getCode()==code)){
            throw new MovieException("No movie with such code");
        }
    }

    // Добавить новый город для заданной страны
    public void addMovie(int code, String name, String director, String producer, String screenwriter,
                        String company, int duration, String country, int year, int genreCode)
            throws GenreException, MovieException {
        Genre genre = getGenre(genreCode);
        if(!containsMovie(code)){
            movies.add(new Movie(code, name, director, producer, screenwriter, company, duration, country, year,
                    genre));
        }else throw new MovieException("Movie with such code already exists");
    }

    public void updateGenre(int code) throws GenreException {
        if (genres == null) {
            genres = new ArrayList<>();
            throw new GenreException("No genre exists yet");
        }
        Genre genre = getGenre(code);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a new genre: ");
        genre.setName(scanner.nextLine());
    }

    public void updateMovie(int code) throws MovieException {
        if(movies==null){
            movies=new ArrayList<>();
            throw new MovieException("No movies exists yet");
        }
        Movie movie = getMovie(code);
        Scanner scanner=new Scanner(System.in);
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
                    movie.setName(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Enter a new film directory: ");
                    movie.setDirector(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Enter a new film producer: ");
                    movie.setProducer(scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Enter a new fil screenwriter: ");
                    movie.setScreenwriter(scanner.nextLine());
                    break;
                case 5:
                    System.out.print("Enter a new production company: ");
                    movie.setCompany(scanner.nextLine());
                    break;
                case 6:
                    System.out.print("Enter duration of the film in minutes: ");
                    movie.setDuration(scanner.nextInt());
                    scanner.nextLine();
                    break;
                case 7:
                    System.out.print("Enter the country: ");
                    movie.setCountry(scanner.nextLine());
                    break;
                case 8:
                    System.out.print("Enter the year of the film premier: ");
                    movie.setYear(scanner.nextInt());
                    scanner.nextLine();
                    break;
                default:
                    next=false;
            }
            System.out.println();
        }
    }

    private boolean containsMovie(int code){
        if(movies==null){
            movies=new ArrayList<>();
            return false;
        }
        for(Movie movie: movies){
            if(movie.getCode()==code)
                return true;
        }
        return false;
    }
    private boolean containsGenre(int code){
        if(genres==null){
            genres=new ArrayList<>();
            return false;
        }
        for(Genre genre: genres){
            if(genre.getCode()==code)
                return true;
        }
        return false;
    }
}
