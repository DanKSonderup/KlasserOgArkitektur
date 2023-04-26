package controller;

import javafx.collections.ObservableList;
import model.Forestilling;
import model.Kunde;
import model.Plads;
import model.Bestilling;
import model.PladsType;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Controller {

    public static Forestilling createForestilling(String navn, LocalDate startDato, LocalDate slutDato) {
        Forestilling forestilling = new Forestilling(navn, startDato, slutDato);
        Storage.storeForestilling(forestilling);
        return forestilling;
    }
    public static Kunde createKunde(String navn, String mobil) {
        Kunde kunde = new Kunde(navn, mobil);
        Storage.storeKunde(kunde);
        return kunde;
    }
    public static Plads createPlads(int række, int nr, int pris, PladsType pladsType) {
        Plads plads = new Plads(række, nr, pris, pladsType);
        Storage.storePlads(plads);
        return plads;
    }

    public static ArrayList<Forestilling> getForestillinger() {
        return new ArrayList<Forestilling>(Storage.getForestillinger());
    }
    public static ArrayList<Plads> convertPladser(ObservableList list) {
        ArrayList<Plads> pladser = new ArrayList<>(list);
        return pladser;
    }
    public static ArrayList<Kunde> getKunder() {
        return new ArrayList<Kunde>(Storage.getKunder());
    }
    public static ArrayList<Plads> getPladser() {
        return new ArrayList<Plads>(Storage.getPladser());
    }
    public static int pladserBestiltForForestilling(Forestilling forestilling, LocalDate dato) {
        return forestilling.antalBestiltePladserPåDag(dato);
    }

    public static Bestilling opretBestillingMedPladser(Forestilling forestilling, Kunde kunde,
    LocalDate dato, ArrayList<Plads> pladser) {

        Bestilling bestilling = null;
        boolean pladserLedige = true;
        // Tjekker om valgte datoer er gyldige for den pågældende forestilling
        if (forestilling.getStartDato().isAfter(dato) && forestilling.getSlutDato().isBefore(dato)) {
            pladserLedige = false;
        }
        int i = 0;
        while (pladserLedige && i < pladser.size()) {
            int række = pladser.get(i).getRække();
            int nr = pladser.get(i).getNr();
            pladserLedige = forestilling.erPladsLedig(række, nr, dato);
            i++;
        }

        if (pladserLedige) {
            bestilling = new Bestilling(dato, kunde, forestilling);
            for (Plads e : pladser) {
                bestilling.addPlads(e);
            }
            forestilling.addBestilling(bestilling);
            kunde.addBestilling(bestilling);
        }
        return bestilling;
    }

}
