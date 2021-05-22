package model.exceptions;

public class MovieException extends Exception{
    public MovieException(String message) {
        super(message);
    }

    public MovieException() {
        super();
    }
}
