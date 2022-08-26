package org.acme;

public class Movie {

    public Movie(int id, String tittle) {
        this.id = id;
        this.tittle = tittle;
    }

    int id;
    String tittle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", tittle='" + tittle + '\'' +
                '}';
    }
}
