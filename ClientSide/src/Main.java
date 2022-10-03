import application.ClientInterface;

public class Main {

    public static void main(String[] args) {
        try {
            ClientInterface clientInterface = new ClientInterface(20001);
            clientInterface.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
