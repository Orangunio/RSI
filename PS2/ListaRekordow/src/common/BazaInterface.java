package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BazaInterface extends Remote {
    List<Produkt> listaProduktow() throws RemoteException; // [cite: 11]
    Produkt szukajPoNazwie(String nazwa) throws RemoteException; // [cite: 9]
}
