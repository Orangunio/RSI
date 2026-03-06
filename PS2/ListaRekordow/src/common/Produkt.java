package common;

import java.io.Serializable;

public class Produkt implements Serializable {
    private String nazwa;
    private double cena;

    public Produkt(String nazwa, double cena) {
        this.nazwa = nazwa;
        this.cena = cena;
    }

    public String getNazwa() { return nazwa; }
    @Override
    public String toString() { return "Produkt: " + nazwa + " | Cena: " + cena + " PLN"; }
}
