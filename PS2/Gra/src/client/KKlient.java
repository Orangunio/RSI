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

            Thread odswiezacz = new Thread(() -> {
                String staraPlansza = "";
                while(true) {
                    try {
                        String obecnaPlansza = gra.stanPlanszy();
                        if(!obecnaPlansza.equals(staraPlansza)) {
                            System.out.println(obecnaPlansza);
                            System.out.println("Obecna tura: " + gra.czyjaTura());
                            staraPlansza = obecnaPlansza;
                        }
                        Thread.sleep(500);
                    } catch(Exception e) {}
                }
            });
            odswiezacz.setDaemon(true);
            odswiezacz.start();

            while(true) {
                if(gra.czyjaTura() == mojSymbol) {
                    System.out.println("TWOJA TURA! Wybierz pole 0-8:");
                    int pole = sc.nextInt();
                    if(!gra.ruch(pole, mojSymbol)) {
                        System.out.println("Nieprawidłowy ruch!");
                    }
                } else {
                    Thread.sleep(1000);
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}
