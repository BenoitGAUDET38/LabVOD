import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args) throws RemoteException {
        Connection connection = new Connection(10001);

        Registry reg = LocateRegistry.createRegistry(2001);
        reg.rebind("MyConnection", connection);

        System.out.println("Objet distant prêt...");
    }
}
