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

    /**
     * Generate the movieList when created
     * @param port
     * @throws RemoteException
     */
    protected VODService(int port) throws RemoteException {
        super(port);

        // fabrication des movies
        movieDescList = new ArrayList<>();
        movieDescList.add(new MovieDesc("Titanic", "001", "Le bateau coule"));
        movieDescList.add(new MovieDescExtended("Sharknado", "002", "Film qui fait peur",
                new byte[][] {"teaser part 1".getBytes(StandardCharsets.UTF_8),
                        "teaser part 2".getBytes(StandardCharsets.UTF_8)}));
        movieDescList.add(new MovieDesc("Interstellar", "003", "Il est aspiré dans unn trou noir"));

        movieList = new ArrayList<>();
        movieList.add(new Movie(movieDescList.get(0), 10, new byte[][] {"first bloc of titanic".getBytes(StandardCharsets.UTF_8),
                "second bloc of titanic".getBytes(StandardCharsets.UTF_8),
                "third bloc of titanic".getBytes(StandardCharsets.UTF_8)}));
        movieList.add(new Movie(movieDescList.get(1), 20, new byte[][] {"first bloc of Sharknado".getBytes(StandardCharsets.UTF_8),
                "second bloc of Sharknado".getBytes(StandardCharsets.UTF_8),
                "third bloc of Sharknado".getBytes(StandardCharsets.UTF_8)}));
        movieList.add(new Movie(movieDescList.get(2), 30, new byte[][] {"first bloc of Interstellar".getBytes(StandardCharsets.UTF_8),
                "second bloc of Interstellar".getBytes(StandardCharsets.UTF_8),
                "third bloc of Interstellar".getBytes(StandardCharsets.UTF_8)}));
    }

    public List<MovieDesc> viewCatalog() {
        return movieDescList;
    }

    /**
     * If the given isbn is correct, play the movie in clientBox with readMovieInThread().
     * Return the bill of the movie.
     *
     * @param isbn
     * @param box
     * @return
     * @throws RemoteException
     */
    public Bill playMovie(String isbn, IClientBox box) throws RemoteException {
        Movie movie = getMovieByIsbn(isbn);
        // gestion cas d'erreur
        if (movie == null)
            return null;
        readMovieInThread(movie, box);

        return movie.generateBill();
    }

    /**
     * Indicated at the clientBox that the movie is currently streaming.
     * After start a threat to read all the blocs of a movie.
     * When the movie is finished, indication that to the clientBox.
     *
     * @param movie
     * @param box
     */
    void readMovieInThread(Movie movie, IClientBox box) {
        try {
            box.setIsStream(true);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    for (byte[] bloc : movie.getContent()) {
                            box.stream(bloc);
                            Thread.sleep(2000);
                    }
                    box.setIsStream(false);
                } catch (InterruptedException | RemoteException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    /**
     * Get a movie in the list if corresponding to the given isbn
     * @param isbn
     * @return
     * @throws RemoteException
     */
    Movie getMovieByIsbn(String isbn) throws RemoteException {
        for (Movie movie : movieList) {
            if (movie.getMovieDesc().getIsbn().equals(isbn))
                return movie;
        }
        return null;
    }
}
