package gui;

import controller.Controller;
import model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class TestApp {

    public static void main(String[] args) {

        initStorage();
        // testPrint();
    }

    public static void initStorage() {
        Forestilling forestilling = Controller.createForestilling("Evita", LocalDate.of(2023, 8, 10), LocalDate.of(2023, 8,20));
        Controller.createForestilling("Lykke Per", LocalDate.of(2023,9,1), LocalDate.of(2023,9,10));
        Controller.createForestilling("Chess", LocalDate.of(2023,8,21), LocalDate.of(2023,8,30));

        Kunde kunde = Controller.createKunde("Anders", "11223344");
        Controller.createKunde("Peter Jensen", "12345678");
        Controller.createKunde("Niels Madsen", "12341234");

        int gul = 500;
        int grøn = 450;
        int blå = 400;
        for (int række = 1; række < 16; række++) {
            for (int nr = 1; nr < 21; nr++) {
                if (række < 6 && (nr < 2 || nr > 18)) {
                    Controller.createPlads(række, nr, grøn, PladsType.STANDARD);
                }
                else if (række < 6) {
                    Controller.createPlads(række,nr,gul,PladsType.STANDARD);
                }
                else if (række == 10 && (nr > 7 && nr < 13)) {
                        Controller.createPlads(række, nr, grøn, PladsType.KØRESTOL);
                } else if (række < 11 && (nr < 2 || nr > 18)) {

                    Controller.createPlads(række,nr,blå,PladsType.STANDARD);

                } else {

                if (række == 11 && (nr > 7 && nr < 13)) {
                    Controller.createPlads(række,nr,blå,PladsType.LANGEBEN);
                } else {
                    Controller.createPlads(række,nr,blå,PladsType.STANDARD);
                }

                }
            }
        }
    }

    public static void testPrint() {
        ArrayList<Forestilling> forestillinger = Storage.getForestillinger();
        System.out.println("Forestillinger:");
        System.out.println("------------------------------");
        for (int i = 0; i < forestillinger.size(); i++) {
            System.out.println(forestillinger.get(i));
        }
        System.out.println("------------------------------");
        System.out.println("Kunder:");
        System.out.println("------------------------------");
        ArrayList<Kunde> kunder = Storage.getKunder();
        for (int i = 0; i < kunder.size(); i++) {
            System.out.println(kunder.get(i));
        }
        System.out.println("------------------------------");
        System.out.println("Pladser:");
        System.out.println("------------------------------");
        ArrayList<Plads> pladser = Storage.getPladser();


        for (Plads plads : pladser) {
            if (plads.getNr() == 20) {
                System.out.printf(plads + "%3s" , " ");
                System.out.println();
            } else {
                System.out.printf(plads + "%3s", " ");
            }

        }
    }


}
