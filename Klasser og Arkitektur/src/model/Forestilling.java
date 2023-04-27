package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Forestilling {
    private String navn;
    private LocalDate startDato;
    private LocalDate slutDato;
    private final ArrayList<Bestilling> bestillinger = new ArrayList<>();

    public Forestilling(String navn, LocalDate startDato, LocalDate slutDato) {
        this.navn = navn;
        this.startDato = startDato;
        this.slutDato = slutDato;
    }

    public void addBestilling(Bestilling bestilling) {
        this.bestillinger.add(bestilling);
    }

    public boolean erPladsLedig(int række,int nr, LocalDate dato) {
        boolean erPladsLedig = true;
        for (Bestilling bestilling: bestillinger) {

            for (Plads plads: bestilling.getPladser()) {
                if (plads.getRække() == række && plads.getNr() == nr && bestilling.getDato().equals(dato)) {
                    erPladsLedig = false;
                }
            }
        }
        return erPladsLedig;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public String getNavn() {
        return navn;
    }

    public ArrayList<Bestilling> getBestillinger() {
        return new ArrayList<Bestilling>(bestillinger);
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }
    public int antalBestiltePladserPåDag(LocalDate dato) {
        int antalPladser = 0;
        for (Bestilling bestilling: bestillinger) {
            if (bestilling.getDato().equals(dato)) {
                antalPladser += bestilling.getPladser().size();
            }
        }
        return antalPladser;
    }

    public LocalDate succesDato() {
        int antalDage = slutDato.compareTo(startDato);
        int bedsteDag = 0;
        LocalDate successDato = startDato;

        for (int i = 0; i <= antalDage; i++) {
           LocalDate countDato = startDato.plusDays(i);
           int solgtePladser = antalBestiltePladserPåDag(countDato);
           if (solgtePladser > bedsteDag) {
               bedsteDag = solgtePladser;
               successDato = startDato.plusDays(i);
           }
        }
        return successDato;
    }





    public String toStringV2() {
        return "Forestilling: " + navn +
                ", startDato: " + startDato +
                ", slutDato: " + slutDato;
    }
    public String toString() {
        return  navn +
                "(fra " + startDato +
                " til " + slutDato + ")";
    }
}
