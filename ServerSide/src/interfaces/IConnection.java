package interfaces;

import exceptions.InvalidCredentialsException;
import exceptions.SignInFailed;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IConnection extends Remote {

    /**
     * Check if the mail and pwd can be used for create a new account.
     * If a new account is created, the informations are register in the csv file and in the client infos list
     *
     * true -> account created
     * otherwise -> throw new SignInFailed exception
     * @param mail
     * @param pwd
     * @return boolean
     * @throws SignInFailed
     * @throws RemoteException
     */
    boolean signIn(String mail, String pwd) throws RemoteException, SignInFailed;

    /**
     * Check if the mail and pwd correspond to an existing account in the client infos list
     * If correct info -> return rmi IVODService
     * Else -> return null
     *
     * @param mail
     * @param pwd
     * @return IVODService
     * @throws RemoteException
     * @throws InvalidCredentialsException
     */
    IVODService login(String mail, String pwd) throws RemoteException, InvalidCredentialsException;
}
