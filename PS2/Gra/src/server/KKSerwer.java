package server;

import common.GraInterface;

import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class KKSerwer extends UnicastRemoteObject implements GraInterface {
    private char[] p = {'-','-','-','-','-','-','-','-','-'};

    protected KKSerwer() throws Exception { super(); }
    public synchronized boolean ruch(int pole, char s) {
        if(p[pole] == '-') { p[pole] = s; return true; }
        return false;
    }
    public synchronized String stanPlanszy() {
        return p[0]+"|"+p[1]+"|"+p[2]+"\n"+p[3]+"|"+p[4]+"|"+p[5]+"\n"+p[6]+"|"+p[7]+"|"+p[8];
    }

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099).rebind("GraKK", new KKSerwer());
            System.out.println("Serwer Gry gotowy.");
        } catch (Exception e) { e.printStackTrace(); }
    }
}
