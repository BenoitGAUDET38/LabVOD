package application;

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

    protected Connection(int port) throws RemoteException {
        super(port);
        vodService = new VODService(10002);
    }

    public boolean signIn(String mail, String pwd) throws SignInFailed {
        System.out.println("Mail : " + mail + ", pwd : " + pwd);
        CSVRegister register = new CSVRegister();
        return register.add(mail, pwd);
    }

    public IVODService login(String mail, String pwd) throws InvalidCredentialsException {


        return (IVODService) vodService;
    }
}
