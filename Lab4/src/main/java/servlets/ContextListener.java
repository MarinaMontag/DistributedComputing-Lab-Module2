package servlets;

import dao.MovieDAO;
import model.Genre;
import model.Movie;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

@WebListener
public class ContextListener implements ServletContextListener {
     ArrayList<Movie> movies;
     ArrayList<Genre> genres=new ArrayList<>();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        final ServletContext servletContext=servletContextEvent.getServletContext();
        this.movies= MovieDAO.selectAll();
        for (Movie movie: movies)
            if(!genres.contains(movie.getGenre()))
                genres.add(movie.getGenre());
        servletContext.setAttribute("movies",movies);
        servletContext.setAttribute("genres", genres);
    }
}
