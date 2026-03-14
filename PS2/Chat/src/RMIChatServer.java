import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;

public class RMIChatServer extends UnicastRemoteObject implements ChatInterface {
    private String ostatniaWiadomosc = "Brak wiadomości";

    protected RMIChatServer() throws Exception { super(); }

    public synchronized void wyslij(String msg) {
        this.ostatniaWiadomosc = msg;
        System.out.println("Nowa wiadomość: " + msg);
    }

    public synchronized String odczytaj() { return ostatniaWiadomosc; }

    public static void main(String[] args) {
        try {
            System.setProperty("java.security.policy", "security.policy");
            System.setProperty("java.rmi.server.hostname", "192.168.7.5");
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("ChatObject", new RMIChatServer());
            System.out.println("Chat Remote Object is ready."); // [cite: 49, 90]
        } catch (Exception e) { e.printStackTrace(); }
    }
}