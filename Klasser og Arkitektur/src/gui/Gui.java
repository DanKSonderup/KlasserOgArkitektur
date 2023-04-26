package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.*;
import storage.Storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Gui extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Teater Bestilling");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private final ListView<Forestilling> lvwForestillinger = new ListView<>();
    private final ListView<Kunde> lvwKunder = new ListView<>();
    private final ListView<Plads> lvwPladser = new ListView<>();
    private final TextField txfName = new TextField();
    private final TextField txfStartDato = new TextField();
    private final TextField txfSlutDato = new TextField();
    private final TextField txfKundeNavn = new TextField();
    private final TextField txfKundeMobil = new TextField();
    private final TextField txfPladsDato = new TextField();
    private final Button btnOpretForestilling = new Button("Opret Forestilling");
    private final Button btnOpretKunde = new Button("Opret Kunde");
    private final Button btnOpretBestilling = new Button("Opret bestilling");
    private final Button btnStatistik = new Button("Statistik");
    private final TextField txfTestDato = new TextField();
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private final Alert success = new Alert(Alert.AlertType.CONFIRMATION);
    private final Alert error = new Alert(Alert.AlertType.ERROR);


    private void initContent(GridPane pane) {
        // show or hide grid lines
        pane.setGridLinesVisible(false);
        // set padding of the pane
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);

        Label lblForestillinger = new Label("Forestillinger");
        pane.add(lblForestillinger,0,0);

        Label lblKunder = new Label("Kunder");
        pane.add(lblKunder,3,0);

        Label lblPladser = new Label("Pladser");
        pane.add(lblPladser,6,0);

        pane.add(lvwForestillinger,0,1, 2,1);
        pane.add(lvwKunder,3,1,2,1);
        pane.add(lvwPladser,6,1,2,1);

        Label lblNavn = new Label("Navn");
        pane.add(lblNavn, 0,2);
        pane.add(txfName,1,2);

        Label lblStartDato = new Label("StartDato");
        pane.add(lblStartDato, 0,3);
        pane.add(txfStartDato,1,3);

        Label lblSlutDato = new Label("SlutDato");
        pane.add(lblSlutDato, 0,4);
        pane.add(txfSlutDato,1,4);

        // Tilføjer Opret forestilling Knap
        pane.add(btnOpretForestilling,1,5);

        Label lblKundeNavn = new Label("Kunde Navn");
        pane.add(lblKundeNavn, 3,2);
        pane.add(txfKundeNavn,4,2);

        Label lblKundeMobil = new Label("Kunde Mobil");
        pane.add(lblKundeMobil,3,3);
        pane.add(txfKundeMobil,4,3);

        Label lblPladsDato = new Label("Dato");
        pane.add(lblPladsDato, 6,2);
        pane.add(txfPladsDato,7,2);

        // Tilføjer Opret kunde Knap
        pane.add(btnOpretKunde,4,4);

        pane.add(btnOpretBestilling, 7,3);
        pane.add(btnStatistik, 7,6);
        pane.add(txfTestDato,7,7);

        lvwForestillinger.getItems().setAll(Controller.getForestillinger());
        lvwKunder.getItems().setAll(Controller.getKunder());
        lvwPladser.getItems().setAll(Controller.getPladser());
        lvwPladser.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        btnOpretKunde.setOnAction(event -> this.opretKundeOnAction());
        btnOpretForestilling.setOnAction(event -> this.opretForestillingOnAction());
        btnOpretBestilling.setOnAction(event -> this.opretBestillingOnAction());
        btnStatistik.setOnAction(event -> this.statistikOnAction());
    }

    public void opretForestillingOnAction() {
        // Opretter dateTimeFormatter til at lave String om til LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        String name = txfName.getText().trim();
        String StartDato = txfStartDato.getText().trim();
        String SlutDato = txfSlutDato.getText().trim();
        LocalDate startDate = null;
        LocalDate slutDate = null;

            try {
                startDate = LocalDate.parse(StartDato, formatter);
                slutDate = LocalDate.parse(SlutDato, formatter);
            }
            catch (DateTimeParseException e) {
            }



        if (name.length() < 1 || StartDato.length() < 1 || SlutDato.length() < 1) {
            alert.setTitle("Tilføj Forestilling");
            alert.setHeaderText("Et er dine felter er tomme");
            alert.setContentText("Udfyld venligst et navn, start og slut-dato");
            alert.show();
        } else if(startDate != null) {
            if (slutDate.isAfter(startDate)) {
                Controller.createForestilling(name, startDate, slutDate);
                lvwForestillinger.getItems().setAll(Controller.getForestillinger());
                txfName.clear();
                txfStartDato.clear();
                txfSlutDato.clear();
            } else {
                alert.setTitle("Tilføj Forestilling");
                alert.setHeaderText("Forkert Dato");
                alert.setContentText("Du har indtastet en slutdato der er før din startdato");
                alert.show();
            }
        } else {
            alert.setTitle("Tilføj Forestilling");
            alert.setHeaderText("Du har indtastet en ugyldig dato");
            alert.setContentText("Datoer skal skrives med følgende format: dd/MM/yyyy - Husk at tjekke om datoen findes");
            alert.show();
        }
    }

    public void opretKundeOnAction() {
        String kundeNavn = txfKundeNavn.getText().trim();
        String kundeMobil = txfKundeMobil.getText().trim();
        if (kundeNavn.length() < 1 || kundeMobil.length() < 1) {
            alert.setTitle("Tilføj Forestilling");
            alert.setHeaderText("Et er dine felter er tomme");
            alert.setContentText("Udfyld venligst et navn og et mobil nummer");
            alert.show();
        } else {
            Controller.createKunde(kundeNavn,kundeMobil);
            lvwKunder.getItems().setAll(Controller.getKunder());
            txfKundeNavn.clear();
            txfKundeMobil.clear();
        }

    }

    public void opretBestillingOnAction() {
        // Opretter dateTimeFormatter til at lave String om til LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        String strengDato = txfPladsDato.getText().trim();
        LocalDate dato = null;

            try {
                dato = LocalDate.parse(strengDato, formatter);
            }
            catch (DateTimeParseException e) {
            }

        if (lvwForestillinger.getSelectionModel().getSelectedItems().isEmpty()
                || lvwKunder.getSelectionModel().getSelectedItems().isEmpty()
                || lvwPladser.getSelectionModel().getSelectedItems().isEmpty()) {
                    alert.setTitle("Opret Bestilling");
                    alert.setHeaderText("Du mangler at vælge et felt");
                    alert.setContentText("Et eller flere felter er ikke markeret");
                    alert.show();
        } else if (dato != null) {
            Bestilling bestilling = Controller.opretBestillingMedPladser(lvwForestillinger.getSelectionModel().getSelectedItem(),lvwKunder.getSelectionModel().getSelectedItem(), dato, Controller.convertPladser(lvwPladser.getSelectionModel().getSelectedItems()));
            if (bestilling != null) {
                String pladser = "";
                success.setTitle("Din bestilling er oprettet!");
                success.setHeaderText("Du har reserveret følgende pladser:");
                for (int i = 0; i < bestilling.getPladser().size(); i++) {
                    pladser += bestilling.getPladser().get(i).toString() + "\n";
                }
                success.setContentText(pladser);
                success.show();
            } else {
                error.setTitle("Kunne ikke oprettes");
                error.setHeaderText("Din bestilling kunne ikke oprettes");
                error.setContentText("Du har forsøgt at købe en plads på en ugyldig dato eller pladsen er allerede købt");
                error.show();
            }


            } else {
                alert.setTitle("Tilføj Bestilling");
                alert.setHeaderText("Forkert Dato");
                alert.setContentText("Du har indtastet en ugyldig dato");
                alert.show();
            }
    }
    public void statistikOnAction() {
        Forestilling forestilling = lvwForestillinger.getSelectionModel().getSelectedItem();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String strengDato = txfTestDato.getText().trim();
        LocalDate dato = null;
        try {
            dato = LocalDate.parse(strengDato, formatter);
        }
        catch (DateTimeParseException e) {
        }
        alert.setTitle("Statistisk");
        alert.setHeaderText("Her er lidt statistik");
        alert.setContentText("Samlet pris: " + Storage.getBestillinger().get(0).samletPris() + "\n" + "Pladser bestilt d. " + strengDato + ": " + Storage.getForestillinger().get(0).antalBestiltePladserPåDag(dato) + "\n" + "Bedste salgsdato for: " + forestilling.getNavn() + "= " + forestilling.succesDato());
        alert.show();
    }



}

