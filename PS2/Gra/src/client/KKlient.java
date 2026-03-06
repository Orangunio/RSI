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
            System.out.println("Grasz jako X. Wybierz pole 0-8:");
            while(true) {
                int pole = sc.nextInt();
                if(gra.ruch(pole, 'X')) System.out.println(gra.stanPlanszy());
                else System.out.println("Pole zajęte!");
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}
