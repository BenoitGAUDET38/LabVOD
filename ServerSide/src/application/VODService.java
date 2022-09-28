package application;

import interfaces.IClientBox;
import interfaces.IVODService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class VODService extends UnicastRemoteObject implements IVODService {
    protected VODService(int port) throws RemoteException {
        super(port);
    }

    public List<MovieDesc> viewCatalog() {
        return null;
    }

    public Bill playMovie(String isbn, IClientBox box) {
        return null;
    }
}
