package dom;

import dom.errors.SimpleErrorHandler;
import javafx.util.Pair;
import model.Genre;
import model.Movie;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DomParser {
    String filename;
    public DomParser(String filename){
        this.filename=filename;
    }
    public Pair<ArrayList<Genre>, ArrayList<Movie>> loadInfo(){
        Document document = getDocument();
        ArrayList<Genre> genres =new ArrayList<>();
        ArrayList<Movie> movies = new ArrayList<>();
        Element root = document.getDocumentElement();
        if(root.getTagName().equals("map")){
            NodeList genreNodes = root.getElementsByTagName("genre");
            for(int i=0;i<genreNodes.getLength();i++){
                Element genreElement=(Element)genreNodes.item(i);
                Genre genre = new Genre();
                genre.setCode(Integer.parseInt(genreElement.getAttribute("id")));
                genre.setName(genreElement.getAttribute("name"));
                genres.add(genre);
                NodeList movieNodes = genreElement.getElementsByTagName("movie");
                for(int j=0;j<movieNodes.getLength();j++){
                    Element movieElement=(Element)movieNodes.item(j);
                    Movie movie = new Movie();
                    movie.setCode(Integer.parseInt(movieElement.getAttribute("id")));
                    movie.setName(movieElement.getAttribute("name"));
                    movie.setDirector(movieElement.getAttribute("director"));
                    movie.setProducer(movieElement.getAttribute("producer"));
                    movie.setScreenwriter(movieElement.getAttribute("screenwriter"));
                    movie.setCompany(movieElement.getAttribute("company"));
                    movie.setDuration(Integer.parseInt(movieElement.getAttribute("duration")));
                    movie.setCountry(movieElement.getAttribute("country"));
                    movie.setYear(Integer.parseInt(movieElement.getAttribute("year")));
                    movie.setGenre(genre);
                    movies.add(movie);
                }
            }

        }
        return new Pair<>(genres, movies);
    }
    private Document getDocument(){
        DocumentBuilderFactory dbf;
        DocumentBuilder db=null;
        SchemaFactory schemaFactory;
        Schema schema;
        try
        {
            schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            schema = schemaFactory.newSchema(new File("src/main/resources/map.xsd"));
            dbf = DocumentBuilderFactory.newInstance();
            dbf.setValidating(false);
            dbf.setSchema(schema);
            db = dbf.newDocumentBuilder();
            db.setErrorHandler(new SimpleErrorHandler());
        }
        catch(ParserConfigurationException | SAXException e)
        {
            System.out.println(e.getMessage());
        }
        Document doc = null;
        try
        {
            doc = db.parse(new File(filename));
        }
        catch (IOException | SAXException ex)
        {
            System.out.println(ex.getMessage());
        }
        return doc;
    }

    public void saveOrUpdate(ArrayList<Genre>genres, ArrayList<Movie>movies){
        DocumentBuilderFactory dbf = null;
        DocumentBuilder db = null;
        Document doc = null;
        try {
            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        // Создаем "чистый" документ XML
        doc = db.newDocument();
        // Создаем корневой элемент
        Element root = doc.createElement("map");
        doc.appendChild(root);
        for(Genre genre: genres){
            Element genreElement = doc.createElement("genre");
            genreElement.setAttribute("id", String.valueOf(genre.getCode()));
            genreElement.setAttribute("name", genre.getName());
            root.appendChild(genreElement);
            for(Movie movie: movies){
                if(movie.getGenre().equals(genre)){
                    Element movieElement = doc.createElement("movie");
                    movieElement.setAttribute("id", String.valueOf(movie.getCode()));
                    movieElement.setAttribute("name", movie.getName());
                    movieElement.setAttribute("director", movie.getDirector());
                    movieElement.setAttribute("producer", movie.getProducer());
                    movieElement.setAttribute("screenwriter", movie.getScreenwriter());
                    movieElement.setAttribute("company", movie.getCompany());
                    movieElement.setAttribute("duration", String.valueOf(movie.getDuration()));
                    movieElement.setAttribute("country", movie.getCountry());
                    movieElement.setAttribute("year", String.valueOf(movie.getYear()));
                    genreElement.appendChild(movieElement);
                }
            }
        }
        try {
            Source domSource = new DOMSource(doc);
            Result fileResult = new StreamResult(new File(filename));
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
            transformer.transform(domSource, fileResult);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

}

