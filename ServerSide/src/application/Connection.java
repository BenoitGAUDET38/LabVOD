package application;

import CSV.CSVLogin;
import CSV.CSVReaderWriter;
import CSV.CSVRegister;
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

    public Connection(int port) throws RemoteException {
        super(port);
        vodService = new VODService(10002);
        clientList = new CSVReaderWriter().getClientInfo();
        System.out.println(clientList);
    }

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

    public IVODService login(String mail, String pwd) throws RemoteException, InvalidCredentialsException{
        System.out.println("* Tentative de connexion de");
        System.out.println("Mail : " + mail + ", pwd : " + pwd);

        ClientInfo currentClient = getClientInfoByMail(mail);
        if(currentClient == null)
            throw new InvalidCredentialsException();

        System.out.println("Compte connecté : " + mail);
        return vodService;
    }

    ClientInfo getClientInfoByMail(String mail) {
        for (ClientInfo clientInfo : clientList) {
            if (clientInfo.getMail().equals(mail))
                return clientInfo;
        }
        return null;
    }
}
