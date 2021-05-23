package model;

import java.io.Serializable;

public class Movie{
    private int code;
    private String name;
    private String director;
    private String producer;
    private String screenwriter;
    private String company;
    private int duration;
    private String country;
    private int year;
    private Genre genre;

    public Movie(){}

    public Movie(int code, String name, String director, String producer, String screenwriter, String company,
                 int duration, String country, int year, Genre genre) {
        this.code = code;
        this.name = name;
        this.director = director;
        this.producer = producer;
        this.screenwriter = screenwriter;
        this.company = company;
        this.duration = duration;
        this.country = country;
        this.year = year;
        this.genre = genre;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDirector() {
        return director;
    }

    public String getProducer() {
        return producer;
    }

    public String getScreenwriter() {
        return screenwriter;
    }

    public String getCompany() {
        return company;
    }

    public int getDuration() {
        return duration;
    }

    public String getCountry() {
        return country;
    }

    public int getYear() {
        return year;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setScreenwriter(String screenwriter) {
        this.screenwriter = screenwriter;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "\t"+name+" -- id = "+code+
                "\n\t\tdirector: "+ director+
                "\n\t\tproducer: "+ producer+
                "\n\t\tscreenwriter: "+ screenwriter+
                "\n\t\tcompany: "+ company+
                "\n\t\tduration: "+ duration+
                "\n\t\tcountry: "+ country+
                "\n\t\tyear: "+ year;
    }
}
