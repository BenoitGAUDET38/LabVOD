package application;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MovieDesc implements Serializable {
    String movieName;
    String isbn;
    String synopsis;
    boolean isExtended;

    public MovieDesc(String movieName, String isbn, String synopsis) throws RemoteException {
        this.movieName = movieName;
        this.isbn = isbn;
        this.synopsis = synopsis;
        this.isExtended = false;
    }

    public MovieDesc(String movieName, String isbn, String synopsis, boolean isExtended) throws RemoteException {
        this.movieName = movieName;
        this.isbn = isbn;
        this.synopsis = synopsis;
        this.isExtended = isExtended;
    }

    public String getMovieName() throws RemoteException {
        return movieName;
    }

    public String getIsbn() throws RemoteException {
        return isbn;
    }

    public String getSynopsis() throws RemoteException {
        return synopsis;
    }
}
