import model.Genre;
import model.Movie;
import model.MovieShop;
import model.exeptions.GenreException;
import model.exeptions.MovieException;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        MovieShop shop = MovieShop.getInstance("src/main/resources/map.xml");
        int command = 0;
        Boolean next = true;
        Scanner scanner = new Scanner(System.in);
            while (next) {
                try {
                    shop.displayShop();
                    menu();
                    command = scanner.nextInt();
                    scanner.nextLine();
                    next = executeCommand(command, shop, scanner);
                } catch (MovieException | GenreException e) {
                    System.out.println(e.getMessage());
                }
            }
    }

    public static void menu(){
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

    public static boolean executeCommand(int command, MovieShop shop, Scanner scanner)
            throws GenreException, MovieException
    {
        switch(command){
            case 1:
                Genre newGenre = setNewGenre(scanner);
                shop.addGenre(newGenre);
                return true;
            case 2:
                setNewCity(scanner, shop);
                return true;
            case 3:
                System.out.print("Enter genre id: ");
                shop.deleteGenre(scanner.nextInt());
                scanner.nextLine();
                return true;
            case 4:
                System.out.print("Enter movie id: ");
                shop.deleteMovie(scanner.nextInt());
                scanner.nextLine();
                return true;
            case 5:
                System.out.print("Enter genre id: ");
                shop.updateGenre(scanner.nextInt());
                scanner.nextLine();
                return true;
            case 6:
                System.out.print("Enter movie id: ");
                shop.updateMovie(scanner.nextInt());
                scanner.nextLine();
                return true;
            case 7:
                System.out.print("Enter genre id: ");
                System.out.println(shop.getGenre(scanner.nextInt()));
                scanner.nextLine();
                System.out.println();
                return true;
            case 8:
                System.out.print("Enter movie id: ");
                System.out.println(shop.getMovie(scanner.nextInt()));
                scanner.nextLine();
                System.out.println();
                return true;
            case 9:
                ArrayList<Genre> genres = shop.getGenres();
                for (Genre genre: genres)
                    System.out.println(genre);
                System.out.println();
                return true;
            case 10:
                System.out.print("Enter genre id: ");
                ArrayList<Movie> movies = shop.getMoviesByGenreId(scanner.nextInt());
                for (Movie movie: movies)
                    System.out.println(movie);
                System.out.println();
                return true;
            default:
                shop.saveToFile(shop.getFileName());
                return false;
        }
    }

    private static Genre setNewGenre(Scanner scanner){
        Genre genre = new Genre();
        System.out.print("Enter genre id: ");
        genre.setCode(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Enter genre name: ");
        genre.setName(scanner.nextLine());
        return genre;
    }

    private static void setNewCity(Scanner scanner, MovieShop shop) throws MovieException, GenreException {
        System.out.print("Enter genre id: ");
        int genreId = scanner.nextInt();
        System.out.print("Enter movie id: ");
        int movieId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter film title: ");
        String title = scanner.nextLine();
        System.out.print("Enter film director: ");
        String director = scanner.nextLine();
        System.out.print("Enter film producer: ");
        String producer = scanner.nextLine();
        System.out.print("Enter film screenwriter: ");
        String screenwriter = scanner.nextLine();
        System.out.print("Enter film company: ");
        String company = scanner.nextLine();
        System.out.print("Enter film duration: ");
        int duration = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter film country: ");
        String country = scanner.nextLine();
        System.out.print("Enter the year of film premier: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        shop.addMovie(movieId, title, director, producer, screenwriter, company, duration, country, year, genreId);
    }
}
