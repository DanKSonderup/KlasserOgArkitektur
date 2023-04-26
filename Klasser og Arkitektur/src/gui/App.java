package gui;

import controller.Controller;
import model.PladsType;

import java.time.LocalDate;

public class App {

        public static void main(String[] args) {
            initItems();
            Gui.launch(Gui.class);
        }

    public static void initItems() {
        Controller.createForestilling("Evita", LocalDate.of(2023, 8, 10), LocalDate.of(2023, 8,20));
        Controller.createForestilling("Lykke Per", LocalDate.of(2023,9,1), LocalDate.of(2023,9,10));
        Controller.createForestilling("Chess", LocalDate.of(2023,8,21), LocalDate.of(2023,8,30));

        Controller.createKunde("Anders", "11223344");
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
    }
