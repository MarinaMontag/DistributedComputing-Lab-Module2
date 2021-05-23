package model;

import java.io.Serializable;
import java.util.Objects;

public class Genre{
    private int code;
    private String name;

    public Genre(){};

    public Genre(int code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;
        Genre genre = (Genre) o;
        return code == genre.code &&
                name.equals(genre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " -- id = "+code;
    }
}
