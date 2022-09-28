package application;

import java.rmi.RemoteException;

public class MovieDescExtended extends MovieDesc{
    byte[] teaser;

    public MovieDescExtended(String movieName, String isbn, String synopsis, byte[] teaser) throws RemoteException {
        super(movieName, isbn, synopsis);
        this.teaser = teaser;
    }
}
