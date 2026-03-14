package server;

import common.GraInterface;

import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class KKSerwer extends UnicastRemoteObject implements GraInterface {
    private char[] p = {'0','1','2','3','4','5','6','7','8'}; // Numery pól pomocniczo
    private char tura = 'X';

    protected KKSerwer() throws Exception { super(); }

    public synchronized boolean ruch(int pole, char s) {
        if(pole < 0 || pole > 8) return false;
        if(s == tura && p[pole] != 'X' && p[pole] != 'O') {
            p[pole] = s;
            tura = (tura == 'X') ? 'O' : 'X';
            return true;
        }
        return false;
    }

    public synchronized char czyjaTura() { return tura; }

    public synchronized String stanPlanszy() {
        return "\n " + p[0]+" | "+p[1]+" | "+p[2]+"\n-----------"+
                "\n " + p[3]+" | "+p[4]+" | "+p[5]+"\n-----------"+
                "\n " + p[6]+" | "+p[7]+" | "+p[8] + "\n";
    }

    public synchronized String sprawdzWygrana() {
        int[][] wygrywajace = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        for(int[] w : wygrywajace) {
            if(p[w[0]] == p[w[1]] && p[w[1]] == p[w[2]]) return "WYGRANA: " + p[w[0]];
        }
        boolean pelna = true;
        for(char pole : p) {
            if(pole != 'X' && pole != 'O') { pelna = false; break; }
        }
        if(pelna) return "REMIS";

        return "GRA W TOKU";
    }

    public static void main(String[] args) {
        try {
            System.setProperty("java.rmi.server.hostname", "10.28.207.24");
            System.setProperty("java.security.policy", "security.policy");

            LocateRegistry.createRegistry(1099).rebind("GraKK", new KKSerwer());
            System.out.println("SERWER: Gotowy do gry sieciowej.");
        } catch (Exception e) { e.printStackTrace(); }
    }
}
