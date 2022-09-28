import exceptions.SignInFailed;
import interfaces.IConnection;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.getRegistry("localhost",2001);
            IConnection c = (IConnection) reg.lookup("MyConnection");

            System.out.println("Sign in : " + c.signIn("thobil@mail.com", "132"));

        } catch (RemoteException | NotBoundException | SignInFailed e) {
            e.printStackTrace();
        }
    }
}
