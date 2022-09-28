package application;

import interfaces.IClientBox;
import interfaces.IVODService;

import java.nio.charset.StandardCharsets;
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
        movieList.add(new Movie(movieDescList.get(0), 10, "Tututututuututututututuuuuuu tutuutu tu tutu. JACK RFESTE SUR CTETTE PORT !!! NON ROSE IL N'y A PLUS DE PALCE :(".getBytes(StandardCharsets.UTF_8)));
        movieList.add(new Movie(movieDescList.get(1), 20, new byte[]{}));
        movieList.add(new Movie(movieDescList.get(2), 30, new byte[]{}));
    }

    public List<MovieDesc> viewCatalog() {
        return movieDescList;
    }

    public Bill playMovie(String isbn, IClientBox box) throws RemoteException {
        Movie movie = getMovieByIsbn(isbn);
        if (movie != null)
            box.stream(movie.getContent()   );
        return null;
    }

    private Movie getMovieByIsbn(String isbn) throws RemoteException {
        for (Movie movie : movieList) {
            if (movie.getMovieDesc().getIsbn().equals(isbn))
                return movie;
        }
        return null;
    }
}
