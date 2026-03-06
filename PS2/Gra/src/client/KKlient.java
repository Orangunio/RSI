package client;

import common.GraInterface;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class KKlient {
    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.getRegistry("192.168.7.5", 1099);
            GraInterface gra = (GraInterface) reg.lookup("GraKK");
            Scanner sc = new Scanner(System.in);

            System.out.print("Wybierz symbol (X/O): ");
            char mojSymbol = sc.next().toUpperCase().charAt(0);
            String ostatniStan = "";

            while(true) {
                String obecnyStan = gra.stanPlanszy();
                char aktualnaTura = gra.czyjaTura();

                // 1. Odśwież widok planszy, jeśli zaszła zmiana
                if (!obecnyStan.equals(ostatniStan)) {
                    System.out.println(obecnyStan);
                    System.out.println("Teraz tura: " + aktualnaTura);
                    ostatniStan = obecnyStan;
                }

                // 2. Jeśli jest moja tura - pytaj o ruch
                if (aktualnaTura == mojSymbol) {
                    System.out.print("TWOJA KOLEJ! Podaj pole (0-8): ");
                    // Program czeka tutaj na Twój ruch
                    if (sc.hasNextInt()) {
                        int pole = sc.nextInt();
                        if (!gra.ruch(pole, mojSymbol)) {
                            System.out.println("Błąd! Pole zajęte lub zły numer.");
                        }
                    }
                } else {
                    // 3. Jeśli nie moja tura - czekaj i sprawdź ponownie za chwilę
                    Thread.sleep(1000);
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}
