package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Bestilling {
    private LocalDate dato;
    private final ArrayList<Plads> pladser = new ArrayList<>();
    private Kunde kunde;
    private Forestilling forestilling;

    public Bestilling(LocalDate dato, Kunde kunde, Forestilling forestilling) {
        this.dato = dato;
        this.kunde = kunde;
        this.forestilling = forestilling;
    }

    public LocalDate getDato() {
        return dato;
    }

    public ArrayList<Plads> getPladser() {
        return new ArrayList<Plads>(pladser);
    }

    public Forestilling getForestilling() {
        return forestilling;
    }

    public void addPlads(Plads plads) {
        this.pladser.add(plads);
    }
    public int samletPris() {
        int total = 0;
        for (Plads plads: pladser) {
            total += plads.getPris();
        }
        return total;
    }
}



