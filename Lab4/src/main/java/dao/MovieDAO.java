package dao;

import dao.utils.JdbcConnection;
import model.Genre;
import model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieDAO {
    private static final String selectQuery = "select * from movies m inner join genres g on m.genre_id=g.id";

    public static ArrayList<Movie> selectAll() {
        ArrayList<Movie> movies = new ArrayList<>();
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(selectQuery);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Movie movie = getMovie(rs);
                Genre genre = new Genre(rs.getInt(11), rs.getString(12));
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
}
