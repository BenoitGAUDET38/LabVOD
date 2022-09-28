package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IMovieDesc extends Remote {
    String getMovieName() throws RemoteException;
    String getIsbn() throws RemoteException;
    String getSynopsis() throws RemoteException;
}
