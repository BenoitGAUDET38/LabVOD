package application;

import interfaces.IClientBox;
import interfaces.IVODService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class VODService extends UnicastRemoteObject implements IVODService {
    private final List<MovieDesc> movieDescList;
    private final List<Movie> movieList;

    protected VODService(int port) throws RemoteException {
        super(port);

        // fabrication des movies
        movieDescList = new ArrayList<>();
        movieDescList.add(new MovieDesc("Titanic", "001", "Le bateau coule"));
        movieDescList.add(new MovieDesc("Sharknado", "002", "Film qui fait peur"));
        movieDescList.add(new MovieDesc("Interstellar", "003", "Il est aspiré dans unn trou noir"));

        movieList = new ArrayList<>();
        movieList.add(new Movie(movieDescList.get(0), 10, new Byte[]{}));
        movieList.add(new Movie(movieDescList.get(1), 20, new Byte[]{}));
        movieList.add(new Movie(movieDescList.get(2), 30, new Byte[]{}));
    }

    public List<MovieDesc> viewCatalog() {
        return movieDescList;
    }

    public Bill playMovie(String isbn, IClientBox box) {
        return null;
    }
}
