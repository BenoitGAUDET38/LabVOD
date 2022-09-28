package application;

import CSV.CSVLogin;
import CSV.CSVRegister;
import exceptions.InvalidCredentialsException;
import exceptions.SignInFailed;
import interfaces.IConnection;
import interfaces.IVODService;

import java.lang.reflect.Array;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Connection extends UnicastRemoteObject implements IConnection {
    Array clientList;
    VODService vodService;

    public Connection(int port) throws RemoteException {
        super(port);
        vodService = new VODService(10002);
    }

    public boolean signIn(String mail, String pwd) throws SignInFailed, RemoteException {
        System.out.println("* Tentative de création de compte de");
        System.out.println("Mail : " + mail + ", pwd : " + pwd);
        CSVRegister register = new CSVRegister();
        boolean r = register.add(mail, pwd);
        System.out.println("Compte créé : " + r);
        return r;
    }

    public IVODService login(String mail, String pwd) throws RemoteException, InvalidCredentialsException{
        System.out.println("* Tentative de connexion de");
        System.out.println("Mail : " + mail + ", pwd : " + pwd);
        boolean r =  new CSVLogin().canLogin(mail, pwd);
        System.out.println("Compte connecté : " + r);

        return r? vodService : null;
    }
}
