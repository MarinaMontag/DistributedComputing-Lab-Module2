package dao;

import dao.utils.JdbcConnection;
import exceptions.GenreException;
import model.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GenreDAO {
    private static final String insertQuery = "insert into genres values (default, ?)";
    private static final String selectQuery = "select * from genres";
    private static final String deleteQuery = "delete from genres where id = ?";
    private static final String updateNameQuery = "update genres set name = ? where id = ?";
    private static final String selectGenreByIdQuery = "select * from genres where id = ?";
    public static void insert(Genre genre) throws GenreException {
        try(Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(insertQuery);
            ps.setString(1, genre.getName());
            int rows=ps.executeUpdate();
            if(rows==0)
                throw new GenreException("Genre insertion failed");
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println(throwables.getMessage());
        }
    }
    public static ArrayList<Genre> selectGenres(){
        ArrayList<Genre> genres=new ArrayList<>();
        try(Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(selectQuery);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Genre genre=getGenre(rs);
                genres.add(genre);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println(throwables.getMessage());
        }
        return genres;
    }

    private static Genre getGenre(ResultSet rs) throws SQLException {
        Genre genre=new Genre();
        genre.setCode(rs.getInt(1));
        genre.setName(rs.getString(2));
        return genre;
    }

    public static void deleteGenreById(int id) throws GenreException {
        try(Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(deleteQuery);
            ps.setInt(1, id);
            int rows=ps.executeUpdate();
            if(rows==0)
                throw new GenreException("Genre with such id was not found");
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println(throwables.getMessage());
        }
    }
    public static void update(int id, String name) throws GenreException{
        try(Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(updateNameQuery);
            ps.setString(1, name);
            ps.setInt(2, id);
            int rows=ps.executeUpdate();
            if(rows==0)
                throw new GenreException("Genre with such id was not found");
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println(throwables.getMessage());
        }
    }
    public static Genre selectGenreById(int id) throws GenreException{
        try(Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(selectGenreByIdQuery);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return getGenre(rs);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println(throwables.getMessage());
        }
        throw new GenreException("Genre with such id was not found");
    }
}
