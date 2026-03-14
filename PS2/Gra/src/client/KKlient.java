package client;

import common.GraInterface;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class KKlient {
    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.getRegistry("10.28.207.24", 1099);
            GraInterface gra = (GraInterface) reg.lookup("GraKK");
            Scanner sc = new Scanner(System.in);

            System.out.print("Wybierz symbol (X/O): ");
            char mojSymbol = sc.next().toUpperCase().charAt(0);
            String ostatniStan = "";

            while(true) {
                String obecnyStan = gra.stanPlanszy();
                char aktualnaTura = gra.czyjaTura();
                String wynik = gra.sprawdzWygrana();

                if (!obecnyStan.equals(ostatniStan)) {
                    System.out.println(obecnyStan);
                    ostatniStan = obecnyStan;
                    if (!wynik.equals("GRA W TOKU")) {
                        System.out.println("KONIEC GRY: " + wynik);
                        break; // Wyjście z pętli - koniec programu
                    }

                    System.out.println("Teraz tura: " + aktualnaTura);
                }
                if (aktualnaTura == mojSymbol) {
                    System.out.print("TWOJA KOLEJ (" + mojSymbol + ")! Podaj pole (0-8): ");
                    if (sc.hasNextInt()) {
                        int pole = sc.nextInt();
                        if (!gra.ruch(pole, mojSymbol)) {
                            System.out.println("Błąd! Pole zajęte, zły numer lub nie Twoja tura.");
                        }
                    } else {
                        sc.next();
                    }
                } else {
                    Thread.sleep(1000);
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}
