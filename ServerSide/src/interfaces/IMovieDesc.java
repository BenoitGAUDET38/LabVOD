package interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IMovieDesc extends Remote, Serializable {
    String getMovieName() throws RemoteException;
    String getIsbn() throws RemoteException;
    String getSynopsis() throws RemoteException;
}
