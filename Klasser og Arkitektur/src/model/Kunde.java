package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Kunde {
    private String navn;
    private String mobil;
    private final ArrayList<Bestilling> bestillinger = new ArrayList<>();

    public Kunde(String navn, String mobil) {
        this.navn = navn;
        this.mobil = mobil;
    }

    public void addBestilling(Bestilling bestilling) {
        this.bestillinger.add(bestilling);
    }
    public ArrayList<Plads> bestiltePladserTilForestillingPådag(Forestilling forestilling, LocalDate dato) {
        ArrayList<Plads> pladser = new ArrayList<>();
        for (Bestilling bestilling: bestillinger) {
            if (bestilling.getForestilling().equals(forestilling) && bestilling.getDato().equals(dato)) {
                for (Plads plads: bestilling.getPladser()) {
                    pladser.add(plads);
                }
            }
        }
        return pladser;
    }

    public String getNavn() {
        return navn;
    }

    @Override
    public String toString() {
        return  navn +  " (" + mobil + ")";
    }
}
