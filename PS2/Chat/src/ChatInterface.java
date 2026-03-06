import java.rmi.*;

public interface ChatInterface extends Remote {
    void wyslij(String msg) throws RemoteException;
    String odczytaj() throws RemoteException;
}