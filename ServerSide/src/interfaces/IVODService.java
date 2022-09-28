package interfaces;

import application.Bill;
import application.MovieDesc;

import java.rmi.RemoteException;
import java.util.List;

public interface IVODService {
    List<MovieDesc> viewCatalog() throws RemoteException;
    Bill playMovie(String isbn, IClientBox box) throws RemoteException;
}
