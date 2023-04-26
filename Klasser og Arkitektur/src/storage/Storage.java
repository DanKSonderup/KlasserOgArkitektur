package storage;

import model.Bestilling;
import model.Forestilling;
import model.Kunde;
import model.Plads;

import java.util.ArrayList;

/*
Tilføj klassen Storage i pakken storage. Klassen skal indeholde lister med systemets
forestillinger, kunder og pladser. Klassen skal også indeholde metoder til at gemme objekter af
3
klasserne Forestilling, Kunde og Plads, og metoder til at hente alle forestillinger, kunder
og pladser.
 */
public abstract class Storage {
    private static ArrayList<Forestilling> forestillinger = new ArrayList<>();
    private static ArrayList<Kunde> kunder = new ArrayList<>();
    private static ArrayList<Plads> pladser = new ArrayList<>();
    private static ArrayList<Bestilling> bestillinger = new ArrayList<>();

    public static void storeForestilling (Forestilling forestilling) {
        forestillinger.add(forestilling);
    }
    public static void storeKunde (Kunde kunde) {
        kunder.add(kunde);
    }
    public static void storePlads (Plads plads) {
        pladser.add(plads);
    }

    public static ArrayList<Forestilling> getForestillinger() {
        return new ArrayList<Forestilling>(forestillinger);
    }

    public static ArrayList<Kunde> getKunder() {
        return new ArrayList<Kunde>(kunder);
    }

    public static ArrayList<Plads> getPladser() {
        return new ArrayList<Plads>(pladser);
    }
    public static void storeBestilling (Bestilling bestilling) {
        bestillinger.add(bestilling);
    }
    public static ArrayList<Bestilling> getBestillinger () {
        return new ArrayList<Bestilling>(bestillinger);
    }
}
