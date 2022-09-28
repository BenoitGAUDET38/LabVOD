package interfaces;

import java.rmi.RemoteException;

public interface IVODService {
    ist<MovieDesc> viewCatalog() throws RemoteException;
    Bill playMovie(String isbn, IClientBox box) throws RemoteException;
}
