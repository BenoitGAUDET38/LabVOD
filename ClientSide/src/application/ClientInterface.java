package application;

import exceptions.InvalidCredentialsException;
import exceptions.SignInFailed;
import interfaces.IClientBox;
import interfaces.IConnection;
import interfaces.IVODService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClientInterface {
    void start() throws Exception {
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
    }

    IVODService connectionChoice(IConnection c) throws SignInFailed, RemoteException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Tapez le numéro associé à votre choix :" +
                    "\n 1 - Se connecter" +
                    "\n 2 - S'incrire");

            switch (scanner.nextLine()) {
                case "1":
                    signIn(c);
                case "2":
                    IVODService ivodService
                default:
                    System.out.println("ERREUR : Choix inexistant");
            }
        }
    }

    void login(IConnection c) {

    }

    boolean signIn(IConnection c) throws SignInFailed, RemoteException {
        Scanner scanner = new Scanner(System.in);
        String mail;
        String password;
        boolean firstTry = true;

        do {
            if (!firstTry) {
                boolean endChoice = false;
                System.out.println("ERREUR : mail ou mot de passe incorrect");
                while (endChoice) {
                    System.out.println("Vous souhaiter : " +
                            "\n 1 - Ressaisir vos identifiants" +
                            "\n 2 - Retourner au menu de connection");

                    switch (scanner.nextLine()) {
                        case "1":
                            endChoice = true;
                            break;
                        case "2":
                            return false;
                        default:
                            System.out.println("ERREUR : Choix inexistant");
                    }
                }
            }

            firstTry = false;
            System.out.println("Saisir un mail : ");
            mail = scanner.nextLine();
            System.out.println("Saisir le mot de passe : ");
            password = scanner.next();
        } while (!c.signIn(mail, password));

        System.out.println("Inscription réussite !");
        return true;
    }
}
