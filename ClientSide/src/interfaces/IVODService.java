package interfaces;

import application.Bill;
import application.MovieDesc;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IVODService extends Remote {

    /**
     * Get the list of movies
     * @return List<MovieDesc> catalog
     */
    List<MovieDesc> viewCatalog() throws RemoteException;

    /**
     * If the given isbn is correct, play the movie in clientBox with readMovieInThread().
     * Return the bill of the movie.
     *
     * @param isbn
     * @param box
     * @return Bill
     * @throws RemoteException
     */
    Bill playMovie(String isbn, IClientBox box) throws RemoteException;
}
