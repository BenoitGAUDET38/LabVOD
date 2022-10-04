package application;

import CSV.CSVReaderWriter;
import exceptions.InvalidCredentialsException;
import exceptions.SignInFailed;
import interfaces.IConnection;
import interfaces.IVODService;

import java.lang.reflect.Array;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class Connection extends UnicastRemoteObject implements IConnection {
    List<ClientInfo> clientList;
    VODService vodService;

    /**
     * Read the clientList from Credentials.csv file and put it in a list
     * @param port
     * @throws RemoteException
     */
    public Connection(int port) throws RemoteException {
        super(port);
        vodService = new VODService(10002);
        clientList = new CSVReaderWriter().getClientInfo();
    }

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
    public boolean signIn(String mail, String pwd) throws SignInFailed, RemoteException {
        System.out.println("* Tentative de création de compte de");
        System.out.println("Mail : " + mail + ", pwd : " + pwd);

        ClientInfo currentClient = getClientInfoByMail(mail);
        if (currentClient != null)
            throw new SignInFailed();

        if (!(new CSVReaderWriter().addClientInfo(mail, pwd)))
            throw new SignInFailed();
        clientList.add(new ClientInfo(mail, pwd));

        System.out.println("Compte créé : " + mail);
        return true;
    }

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
    public IVODService login(String mail, String pwd) throws RemoteException, InvalidCredentialsException{
        System.out.println("* Tentative de connexion de");
        System.out.println("Mail : " + mail + ", pwd : " + pwd);

        ClientInfo currentClient = getClientInfoByMail(mail);
        if(currentClient == null)
            throw new InvalidCredentialsException();

        if(!currentClient.getMail().equals(mail))
            throw new InvalidCredentialsException();
        System.out.println("Mail ok");

        if(!currentClient.getPassword().equals(pwd))
            throw new InvalidCredentialsException();
        System.out.println("Password ok");

        System.out.println("Compte connecté : " + mail);
        return vodService;
    }

    /**
     * Check if the mail correspond to a ClientInfo
     * @param mail
     * @return
     */
    ClientInfo getClientInfoByMail(String mail) {
        for (ClientInfo clientInfo : clientList) {
            if (clientInfo.getMail().equals(mail))
                return clientInfo;
        }
        return null;
    }
}
