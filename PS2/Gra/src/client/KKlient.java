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
            System.out.print("Wybierz swój symbol (X lub O): ");
            char mojSymbol = sc.next().toUpperCase().charAt(0);

            System.out.println("Połączono! Czekaj na ruchy...");

            String ostatniStan = "";
            while(true) {
                String stan = gra.stanPlanszy();
                String wynik = gra.sprawdzWygrana();

                if(!stan.equals(ostatniStan)) {
                    System.out.println(stan);
                    ostatniStan = stan;
                    if(!wynik.equals("GRA W TOKU")) {
                        System.out.println("KONIEC GRY! " + wynik);
                        break;
                    }
                }
                if(gra.czyjaTura() == mojSymbol) {
                    System.out.print("TWOJA TURA (" + mojSymbol + "). Podaj pole 0-8: ");
                    int pole = sc.nextInt();
                    if(!gra.ruch(pole, mojSymbol)) {
                        System.out.println("Błąd: Nie twoja tura lub pole zajęte!");
                    }
                } else {
                    // Czekanie
                    Thread.sleep(1000);
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}
