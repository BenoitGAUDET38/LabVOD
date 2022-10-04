package application;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Arrays;

public class MovieDescExtended extends MovieDesc implements Serializable {
    byte[][] teaser;

    public MovieDescExtended(String movieName, String isbn, String synopsis, byte[][] teaser) throws RemoteException {
        super(movieName, isbn, synopsis, true);
        this.teaser = teaser;
    }

    /**
     * Display the teaser in the current interface
     * @throws InterruptedException
     */
    public void displayTeaser() throws InterruptedException {
        for (byte[] bloc : teaser) {
            System.out.println(Arrays.toString(bloc));
            Thread.sleep(100);
        }
    }
}
