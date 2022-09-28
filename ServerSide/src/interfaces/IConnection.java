package interfaces;

import exceptions.InvalidCredentialsException;
import exceptions.SignInFailed;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IConnection extends Remote {
    boolean signIn(String mail, String pwd) throws RemoteException, SignInFailed;
    IVODService login(String mail, String pwd) throws RemoteException, InvalidCredentialsException;
}
