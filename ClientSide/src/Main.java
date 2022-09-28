import application.ClientBox;
import application.MovieDesc;
import exceptions.InvalidCredentialsException;
import exceptions.SignInFailed;
import interfaces.IClientBox;
import interfaces.IConnection;
import interfaces.IMovieDesc;
import interfaces.IVODService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.getRegistry("localhost",2001);
            IConnection c = (IConnection) reg.lookup("MyConnection");

            System.out.println("Sign in : " + c.signIn("thobil@mail.com", "132"));

            IVODService ivodService = c.login("thobil@mail.com", "132");
            System.out.println("\nRécupération de toutes les desciptions :");
            for (MovieDesc movieDesc : ivodService.viewCatalog()) {
                System.out.println("-> name : " + movieDesc.getMovieName() +
                                 "\n   isbn : " + movieDesc.getIsbn() +
                                 "\n   syno : " + movieDesc.getSynopsis());
            }

            // on creer le stub de notre ClientBox
            IClientBox iClientBox = (IClientBox) new ClientBox(30001);

            System.out.println("On veut regarder titanic :");
            ivodService.playMovie(ivodService.viewCatalog().get(0).getIsbn(), iClientBox);

        } catch (RemoteException | NotBoundException | SignInFailed | InvalidCredentialsException e) {
            e.printStackTrace();
        }
    }
}
