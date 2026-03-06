package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GraInterface extends Remote {
    boolean ruch(int pole, char s) throws RemoteException;
    String stanPlanszy() throws RemoteException;
    char czyjaTura() throws RemoteException;
    String sprawdzWygrana() throws RemoteException;
}
