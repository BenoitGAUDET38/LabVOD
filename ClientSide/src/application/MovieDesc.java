package application;

import interfaces.IMovieDesc;

import java.io.Serializable;
import java.rmi.RemoteException;

public class MovieDesc implements IMovieDesc, Serializable {
    String movieName;
    String isbn;
    String synopsis;

    public MovieDesc(String movieName, String isbn, String synopsis) throws RemoteException {
        this.movieName = movieName;
        this.isbn = isbn;
        this.synopsis = synopsis;
    }

    @Override
    public String getMovieName() throws RemoteException {
        return movieName;
    }

    @Override
    public String getIsbn() throws RemoteException {
        return isbn;
    }

    @Override
    public String getSynopsis() throws RemoteException {
        return synopsis;
    }
}
