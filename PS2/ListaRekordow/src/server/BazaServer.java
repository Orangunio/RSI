package server;

import common.BazaInterface;
import common.Produkt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class BazaServer extends UnicastRemoteObject implements BazaInterface {
    private List<Produkt> baza;

    protected BazaServer() throws RemoteException {
        baza = new ArrayList<>();
        baza.add(new Produkt("Laptop", 3500.0));
        baza.add(new Produkt("Mysz", 150.0));
    }

    @Override
    public List<Produkt> listaProduktow() { return baza; }

    @Override
    public Produkt szukajPoNazwie(String nazwa) {
        return baza.stream()
                .filter(p -> p.getNazwa().equalsIgnoreCase(nazwa))
                .findFirst().orElse(null);
    }

    public static void main(String[] args) {
        try {
            // Wartość routowalna z innych komputerów
            System.setProperty("java.security.policy", "java.policy");
            System.setProperty("java.rmi.server.hostname", "192.168.7.5");
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("BazaSerwis", new BazaServer());
            System.out.println("Serwer Bazy gotowy...");
        } catch (Exception e) { e.printStackTrace(); }
    }
}
