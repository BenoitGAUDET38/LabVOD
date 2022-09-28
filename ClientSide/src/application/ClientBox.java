package application;

import interfaces.IClientBox;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

public class ClientBox extends UnicastRemoteObject implements IClientBox {
    public ClientBox(int port) throws RemoteException {
        super(port);
    }

    public void stream(byte[] chunck) {
        System.out.println(Arrays.toString(chunck));
    }
}
