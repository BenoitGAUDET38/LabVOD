import application.ClientBox;
import application.MovieDesc;
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

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static IConnection c;
    static IVODService ivodService = null;

    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.getRegistry("localhost",2001);
            c = (IConnection) reg.lookup("MyConnection");

            //login and register
            while (ivodService == null){
                System.out.println("#-------------------------------------------#");
                System.out.println("Write the number of your choice");
                System.out.println("1 : login");
                System.out.println("2 : register");
                int a = scanner.nextInt();
                scanner.nextLine();
                if(a == 1) ivodService = login();
                else if(a == 2) register();
            }

            System.out.println("Login successfully");
            showMovieList();

            chooseMovie();

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    private static void chooseMovie() throws RemoteException {
        // on creer le stub de notre ClientBox
        IClientBox iClientBox = (IClientBox) new ClientBox(30001);
        System.out.println("Choose your movie by taping his name");
        String filmName = scanner.nextLine();

        for(int i = 0; i < ivodService.viewCatalog().size(); i++){
            if(ivodService.viewCatalog().get(i).getMovieName().equals(filmName)){
                ivodService.playMovie(ivodService.viewCatalog().get(i).getIsbn(), iClientBox);
                break;
            }
        }
    }

    private static IVODService login() throws RemoteException{
        sleep();
        System.out.println("Login ");
        System.out.println("mail :");
        String mail = scanner.nextLine();

        sleep();
        System.out.println("password :");
        String pwd = scanner.nextLine();

        try {
            return c.login(mail, pwd);
        }catch (InvalidCredentialsException e){
            System.out.println("login invalid");
        }
        return null;
    }

    private static void register() throws RemoteException{
        System.out.println("New account ");
        System.out.println("mail :");
        String mail = scanner.nextLine();
        System.out.println("password :");
        String pwd = scanner.nextLine();
        try {
            c.signIn(mail, pwd);
        }catch (SignInFailed e){
            System.out.println("Sign in impossible");
        }
    }

    private static void showMovieList() throws RemoteException {
        System.out.println("\nRécupération de toutes les desciptions :");
        for (MovieDesc movieDesc : ivodService.viewCatalog()) {
            System.out.println("-> name : " + movieDesc.getMovieName() +
                    "\n   isbn : " + movieDesc.getIsbn() +
                    "\n   syno : " + movieDesc.getSynopsis());
        }
    }

    private static void sleep(){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }
}
