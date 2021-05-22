package dao;

import model.Genre;
import model.Movie;
import model.exceptions.GenreException;
import model.exceptions.MovieException;
import dao.utils.JdbcConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieDAO {
    private static final String insertQuery = "insert into movies values (default, ?, ?, ?, ?, ?, ? ,? ,?, ?)";
    private static final String selectQuery = "select * from movies";
    private static final String deleteQuery = "delete from movies where id = ?";
    private static final String deleteByGenreIdQuery = "delete from movies where genre_id = ?";
    private static final String updateNameQuery = "update movies set name = ? where id = ?";
    private static final String updateDirectorQuery = "update movies set director = ? where id = ?";
    private static final String updateProducerQuery = "update movies set producer = ? where id = ?";
    private static final String updateScreenwriterQuery = "update movies set screenwriter = ? where id = ?";
    private static final String updateCompanyQuery = "update movies set company = ? where id = ?";
    private static final String updateDurationQuery = "update movies set duration = ? where id = ?";
    private static final String updateCountryQuery = "update movies set country = ? where id = ?";
    private static final String updateYearQuery = "update movies set year = ? where id = ?";
    private static final String selectMoviesByGenreIdQuery = "select * from movies where genre_id = ?";
    private static final String selectMovieByNameQuery = "select * from movies where name = ?";
    private static final String selectCountOfMoviesByGenreId="select count(*) from movies where genre_id = ?";

    public static void insert(Movie movie){
        try(Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(insertQuery);
            ps.setString(1, movie.getName());
            ps.setString(2, movie.getDirector());
            ps.setString(3, movie.getProducer());
            ps.setString(4, movie.getScreenwriter());
            ps.setString(5, movie.getCompany());
            ps.setInt(6, movie.getDuration());
            ps.setString(7, movie.getCountry());
            ps.setInt(8, movie.getYear());
            ps.setInt(9, movie.getGenre().getCode());
            int rows=ps.executeUpdate();
            if(rows==0)
                throw new MovieException("Movie insertion failed");
        } catch (SQLException | ClassNotFoundException | MovieException throwables) {
            System.out.println(throwables.getMessage());
        }
    }
    public static ArrayList<Movie> selectAll(){
        ArrayList<Movie>movies = new ArrayList<>();
        try(Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(selectQuery);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Movie movie = getMovie(rs);
                Genre genre = new Genre();
                genre.setCode(rs.getInt(10));
                movie.setGenre(genre);
                movies.add(movie);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println(throwables.getMessage());
        }
        return movies;
    }

    private static Movie getMovie(ResultSet rs) throws SQLException {
        Movie movie = new Movie();
        movie.setCode(rs.getInt(1));
        movie.setName(rs.getString(2));
        movie.setDirector(rs.getString(3));
        movie.setProducer(rs.getString(4));
        movie.setScreenwriter(rs.getString(5));
        movie.setCompany(rs.getString(6));
        movie.setDuration(rs.getInt(7));
        movie.setCountry(rs.getString(8));
        movie.setYear(rs.getInt(9));
        return movie;
    }

    public static void deleteMovieById(int id) throws MovieException {
        try(Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(deleteQuery);
            ps.setInt(1, id);
            int rows=ps.executeUpdate();
            if(rows==0)
                throw new MovieException("Movie with such id was not found");
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println(throwables.getMessage());
        }
    }
    public static void deleteByGenreId(int id) throws MovieException {
        try(Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(deleteByGenreIdQuery);
            ps.setInt(1, id);
            int rows=ps.executeUpdate();
            if(rows==0)
                throw new MovieException("Movies with such genre id were not found");
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    public static void updateName(int id, String name) throws MovieException {
        try(Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(updateNameQuery);
            ps.setString(1, name);
            ps.setInt(2, id);
            int rows=ps.executeUpdate();
            if(rows==0)
                throw new MovieException("Movie with such id was not found");
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println(throwables.getMessage());
        }
    }
    public static void updateDirector(int id, String director) throws MovieException {
        try(Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(updateDirectorQuery);
            ps.setString(1, director);
            ps.setInt(2, id);
            int rows=ps.executeUpdate();
            if(rows==0)
                throw new MovieException("Movie with such id was not found");
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println(throwables.getMessage());
        }
    }
    public static void updateProducer(int id, String producer) throws MovieException {
        try(Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(updateProducerQuery);
            ps.setString(1, producer);
            ps.setInt(2, id);
            int rows=ps.executeUpdate();
            if(rows==0)
                throw new MovieException("Movie with such id was not found");
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println(throwables.getMessage());
        }
    }
    public static void updateScreenwriter(int id, String screenwriter) throws MovieException {
        try(Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(updateScreenwriterQuery);
            ps.setString(1, screenwriter);
            ps.setInt(2, id);
            int rows=ps.executeUpdate();
            if(rows==0)
                throw new MovieException("Movie with such id was not found");
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println(throwables.getMessage());
        }
    }
    public static void updateCompany(int id, String company) throws MovieException {
        try(Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(updateCompanyQuery);
            ps.setString(1, company);
            ps.setInt(2, id);
            int rows=ps.executeUpdate();
            if(rows==0)
                throw new MovieException("Movie with such id was not found");
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println(throwables.getMessage());
        }
    }
    public static void updateDuration(int id, int duration) throws MovieException {
        try(Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(updateDurationQuery);
            ps.setInt(1, duration);
            ps.setInt(2, id);
            int rows=ps.executeUpdate();
            if(rows==0)
                throw new MovieException("Movie with such id was not found");
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println(throwables.getMessage());
        }
    }
    public static void updateCountry(int id, String country) throws MovieException {
        try(Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(updateCountryQuery);
            ps.setString(1, country);
            ps.setInt(2, id);
            int rows=ps.executeUpdate();
            if(rows==0)
                throw new MovieException("Movie with such id was not found");
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println(throwables.getMessage());
        }
    }
    public static void updateYear(int id, int year) throws MovieException {
        try(Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(updateYearQuery);
            ps.setInt(1, year);
            ps.setInt(2, id);
            int rows=ps.executeUpdate();
            if(rows==0)
                throw new MovieException("Movie with such id was not found");
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println(throwables.getMessage());
        }
    }

    public static ArrayList<Movie> getMoviesByGenreId(int id) throws MovieException {
        ArrayList<Movie> movies=new ArrayList<>();
        try(Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(selectMoviesByGenreIdQuery);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Movie movie=getMovie(rs);
                movies.add(movie);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println(throwables.getMessage());
        }
        if(movies.size()==0)
            throw new MovieException("No movies with such genre id");
        return movies;
    }

    public static Movie getMovie(String name) throws MovieException {
        Movie movie=new Movie();
        try(Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(selectMovieByNameQuery);
            ps.setString(1, name);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
               movie=getMovie(rs);
            } else throw new MovieException("No movie with such id");
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println(throwables.getMessage());
        }
        return movie;
    }
    public static int getAmountOfMoviesWithGenreId(int id) throws GenreException {
        int count = 0;
        try(Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(selectCountOfMoviesByGenreId);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                count=rs.getInt(1);
            } else throw new GenreException("No genre with such id");
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println(throwables.getMessage());
        }
        return count;
    }
}
