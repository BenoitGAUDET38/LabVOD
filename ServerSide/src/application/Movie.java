package application;

public class Movie {
    private MovieDesc movieDesc;
    private float price;
    private Byte[] content;

    public Movie(MovieDesc movieDesc, float price, Byte[] content) {
        this.movieDesc = movieDesc;
        this.price = price;
        this.content = content;
    }
}
