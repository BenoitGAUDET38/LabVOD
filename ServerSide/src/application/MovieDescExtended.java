package application;

import java.rmi.RemoteException;

public class MovieDescExtended extends MovieDesc{
    Bytes[] teaser;

    public MovieDescExtended(String movieName, String isbn, String synopsis, Bytes[] teaser) throws RemoteException {
        super(movieName, isbn, synopsis);
        this.teaser = teaser;
    }
}
