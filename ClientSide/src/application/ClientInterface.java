package application;

import exceptions.InvalidCredentialsException;
import exceptions.SignInFailed;
import interfaces.IClientBox;
import interfaces.IConnection;
import interfaces.IVODService;

import javax.security.auth.login.LoginException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ClientInterface {
    private int port;

    public ClientInterface(int port) {
        this.port = port;
    }

    /**
     * Start the user interface and the communication with the server
     * @throws Exception
     */
    public void start() throws Exception {
        Registry reg = LocateRegistry.getRegistry("localhost",2001);
        IConnection c = (IConnection) reg.lookup("MyConnection");

        IVODService ivodService = connectionChoice(c);
        if (ivodService == null)
            return;

        chooseMovie(ivodService);
    }

    /**
     * Ask the connection action to do.
     * After that redirect to the correct actions.
     * @param c
     * @return
     * @throws RemoteException
     */
    IVODService connectionChoice(IConnection c) throws RemoteException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Tapez le numéro associé à votre choix de connexion :" +
                    "\n 1 - Se connecter" +
                    "\n 2 - S'incrire" +
                    "\n 3 - Quitter l'application");

            switch (scanner.nextLine()) {
                case "1" -> {
                    try{
                        IVODService ivodService = login(c);
                        if (ivodService != null)
                            return ivodService;
                    }catch (InvalidCredentialsException le){
                        System.out.println("Connection impossible avec les données renseignées.");
                    }
                }
                case "2" -> {
                    try{
                        signIn(c);
                    }catch (SignInFailed sif){
                        System.out.println("Inscription impossible avec les données renseignées.");
                    }
                }
                case "3" -> {
                    return null;
                }
                default -> System.out.println("ERREUR : Choix inexistant");
            }
        }
    }

    /**
     * Display the list of movie taken from the server and the user can make his choice
     * @param ivodService
     * @throws RemoteException
     * @throws InterruptedException
     */
    void chooseMovie(IVODService ivodService) throws RemoteException, InterruptedException {
        List<MovieDesc> catalog = ivodService.viewCatalog();
        int nbMovies;
        while (true) {
            nbMovies = displayCatalog(catalog);

            int choice = -1;
            do {
                System.out.println("Choisir un film à regarder ou quitter l'application avec 0 : ");
                try {
                    Scanner scanner = new Scanner(System.in);
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice < 0 || choice > nbMovies)
                        System.out.println("Erreur : choix incorrect");
                } catch (NumberFormatException numberFormatException) {
                    System.out.println("Erreur : le choix n'est pas un numéro");
                }
            } while (choice < 0 || choice > nbMovies);

            // On quitte l'application
            if (choice == 0)
                return;

            watchMovie(ivodService, catalog.get(choice-1).getIsbn());
        }
    }

    /**
     * Display a given catalog
     * @param catalog
     * @return
     * @throws RemoteException
     * @throws InterruptedException
     */
    int displayCatalog(List<MovieDesc> catalog) throws RemoteException, InterruptedException {
        System.out.println("\nAffichage du choix des films :");
        int nbMovies = 0;
        for (MovieDesc movieDesc : catalog) {
            nbMovies++;
            System.out.println(nbMovies + " -> name : " + movieDesc.getMovieName() +
                    "\n     isbn : " + movieDesc.getIsbn() +
                    "\n     syno : " + movieDesc.getSynopsis());
            if (movieDesc.isExtended) {
                MovieDescExtended movieDescExtended = (MovieDescExtended) movieDesc;
                System.out.println("     Affichage du trailer : ");
                movieDescExtended.displayTeaser();
            }
        }

        return nbMovies;
    }

    /**
     * Actions to do when a user watch a movie.
     * Wait the end of the movie before display the bill.
     * @param ivodService
     * @param movieIsbn
     * @throws RemoteException
     * @throws InterruptedException
     */
    void watchMovie(IVODService ivodService, String movieIsbn) throws RemoteException, InterruptedException {
        IClientBox clientBox = new ClientBox(port);
        Bill bill = ivodService.playMovie(movieIsbn, clientBox);

        while (clientBox.getIsStream()) {
            // on attend jusqu'à que le film soit terminé
            Thread.sleep(1000);
        }

        System.out.println("FILM TERMINE !");
        System.out.println("Facture reçu de " + bill.outrageousPrice + "€ pour le film " + bill.movieName + "...");
    }

    /**
     * Ask client information for login with the server
     * @param c
     * @return
     * @throws InvalidCredentialsException
     * @throws RemoteException
     */
    IVODService login(IConnection c) throws InvalidCredentialsException, RemoteException {
        Scanner scanner = new Scanner(System.in);
        String mail;
        String password;
        boolean firstTry = true;
        IVODService ivodService;

        do {
            if (!firstTry) {
                boolean endChoice = false;
                System.out.println("ERREUR : mail ou mot de passe incorrect");
                while (!endChoice) {
                    System.out.println("Vous souhaiter : " +
                            "\n 1 - Ressaisir vos identifiants" +
                            "\n 2 - Retourner au menu de connection");

                    switch (scanner.nextLine()) {
                        case "1" -> endChoice = true;
                        case "2" -> {
                            return null;
                        }
                        default -> System.out.println("ERREUR : Choix inexistant");
                    }
                }
            }

            firstTry = false;
            System.out.println("Saisir un mail : ");
            mail = scanner.nextLine();
            System.out.println("Saisir le mot de passe : ");
            password = scanner.next();
        } while ((ivodService = c.login(mail, password)) == null);

        System.out.println("Connexion réussite !");
        return ivodService;
    }

    /**
     * Ask client information for sign in with the server
     * @param c
     * @return
     * @throws SignInFailed
     * @throws RemoteException
     */
    boolean signIn(IConnection c) throws SignInFailed, RemoteException {
        Scanner scanner = new Scanner(System.in);
        String mail;
        String password;
        boolean firstTry = true;

        do {
            if (!firstTry) {
                boolean endChoice = false;
                System.out.println("ERREUR : mail déjà existant ou erroné");
                while (!endChoice) {
                    System.out.println("Vous souhaiter : " +
                            "\n 1 - Ressaisir vos identifiants" +
                            "\n 2 - Retourner au menu de connection");

                    switch (scanner.nextLine()) {
                        case "1" -> endChoice = true;
                        case "2" -> {
                            return false;
                        }
                        default -> System.out.println("ERREUR : Choix inexistant");
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
