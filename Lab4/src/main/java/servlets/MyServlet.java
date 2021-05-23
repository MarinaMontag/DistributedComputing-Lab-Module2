package servlets;

import model.Genre;
import model.Movie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(urlPatterns = {"/servlet"})
public class MyServlet extends HttpServlet {
    ArrayList<Movie> movies;
    ArrayList<Genre> genres=new ArrayList<>();
    @Override
    public void init() throws ServletException {
        final Object movies=getServletContext().getAttribute("movies");
        final Object genres=getServletContext().getAttribute("genres");
        if(!(movies instanceof ArrayList)||!(genres instanceof ArrayList))
            throw new IllegalStateException("Your repo isn't initialized correctly");
        else{
            this.movies =(ArrayList<Movie>)movies;
            this.genres=(ArrayList<Genre>)genres;
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("movies",movies);
        req.setAttribute("genres",genres);
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req,resp);
    }
}
