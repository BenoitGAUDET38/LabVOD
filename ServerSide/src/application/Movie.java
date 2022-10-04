package application;

import java.math.BigInteger;
import java.rmi.RemoteException;

public class Movie {
    private MovieDesc movieDesc;
    private float price;
    private byte[][] content;

    public Movie(MovieDesc movieDesc, float price, byte[][] content) {
        this.movieDesc = movieDesc;
        this.price = price;
        this.content = content;
    }

    public MovieDesc getMovieDesc() {
        return movieDesc;
    }

    public float getPrice() {
        return price;
    }

    public byte[][] getContent() {
        return content;
    }

    /**
     * Generate a bill link with the movie informations
     * @return
     * @throws RemoteException
     */
    public Bill generateBill() throws RemoteException {
        return new Bill(movieDesc.getMovieName(), price);
    }
}
