package application;

import interfaces.IClientBox;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

public class ClientBox extends UnicastRemoteObject implements IClientBox {
    private boolean isStream;

    public ClientBox(int port) throws RemoteException {
        super(port);
        this.isStream = false;
    }

    public void stream(byte[] chunck) {
        System.out.println(Arrays.toString(chunck));
    }

    public void setIsStream(boolean isStream) {
        this.isStream = isStream;
    };

    public boolean getIsStream() {
        return isStream;
    }
}
