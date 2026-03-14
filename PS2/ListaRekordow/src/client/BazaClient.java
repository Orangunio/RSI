package client;

import common.BazaInterface;
import common.Produkt;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class BazaClient {
    public static void main(String[] args) {
        try {
            System.setProperty("java.security.policy", "java.policy");
            Registry registry = LocateRegistry.getRegistry("192.168.7.5", 1099);

            BazaInterface baza = (BazaInterface) registry.lookup("BazaSerwis");

            // 3. Pobranie i wyświetlenie pełnej listy rekordów (Ćwiczenie 1, pkt 8)
            System.out.println("--- Pobieranie listy produktów z serwera ---");
            List<Produkt> produkty = baza.listaProduktow();
            for (Produkt p : produkty) {
                System.out.println(p);
            }

            // 4. Wyszukiwanie rekordu po nazwie (Ćwiczenie 1, pkt 9)
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nPodaj nazwę produktu do wyszukania: ");
            String nazwa = scanner.nextLine();

            Produkt znaleziony = baza.szukajPoNazwie(nazwa);

            if (znaleziony != null) {
                System.out.println("Znaleziono: " + znaleziony);
            } else {
                System.out.println("Nie znaleziono produktu o nazwie: " + nazwa);
            }

        } catch (Exception e) {
            System.err.println("Błąd klienta: " + e.toString());
            e.printStackTrace();
        }
    }
}