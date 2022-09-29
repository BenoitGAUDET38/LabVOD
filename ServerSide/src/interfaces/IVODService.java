package interfaces;

import application.Bill;
import application.Movie;
import application.MovieDesc;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IVODService extends Remote {
    List<MovieDesc> viewCatalog() throws RemoteException;
    Bill playMovie(String isbn, IClientBox box) throws RemoteException;
}
