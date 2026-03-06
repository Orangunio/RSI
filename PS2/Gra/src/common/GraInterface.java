package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GraInterface extends Remote {
    boolean ruch(int pole, char symbol) throws RemoteException;
    String stanPlanszy() throws RemoteException;
}
