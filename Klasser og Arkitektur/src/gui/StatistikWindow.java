package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import model.Forestilling;
import model.Kunde;
import model.Plads;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class StatistikWindow extends Stage {
    private final Forestilling forestilling; //nullable
    private final Kunde kunde; //nullable

    public StatistikWindow(String title, Forestilling forestilling, Kunde kunde) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.forestilling = forestilling;
        this.kunde = kunde;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    // -------------------------------------------------------------------------
    // Data felter for vores GridPane

    private final TextField txfDato = new TextField();
    private final TextField txfForestillingPladserPåDag = new TextField();
    private final TextField txfSuccesDato = new TextField();
    private final ListView<Plads> lvwPladser = new ListView<>();

    private final Button btnBeregnStatistisk = new Button("Vis statistisk");
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);


    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        // Creating Labels
        Label lblDatoPladserPåDag = new Label("Indtast dato");
        lblDatoPladserPåDag.setAlignment(Pos.CENTER);
        Label lblInfoPladserPåDag = new Label("Antal pladser solgt for " + forestilling.getNavn());
        Label lblSuccesDato = new Label("Bedst sælgende dato for " + forestilling.getNavn());

        // Adding GUI Items
        pane.add(lblDatoPladserPåDag,0,0);
        pane.add(txfDato,0,1);
        txfDato.setPromptText("YYYY/MM/D");
        pane.add(btnBeregnStatistisk,0,2);
        pane.add(lblInfoPladserPåDag,0,3);
        pane.add(txfForestillingPladserPåDag,0,4);
        txfForestillingPladserPåDag.setEditable(false);
        pane.add(lblSuccesDato,0,5);
        pane.add(txfSuccesDato,0,6);
        txfSuccesDato.setEditable(false);
        if (Controller.getSuccesDatoForestilling(forestilling) != null) {
            txfSuccesDato.setText("" + forestilling.succesDato());
        }


        if (kunde != null) {
            Label lblKøbtePladserPåDag = new Label("Pladser købt af " + kunde.getNavn());
            pane.add(lblKøbtePladserPåDag,0,7);
            pane.add(lvwPladser,0,8);
            lvwPladser.setPrefHeight(100);
        }

        btnBeregnStatistisk.setOnAction(event -> this.statistikOnAction());
    }

    public void statistikOnAction() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");

        String strengDato = txfDato.getText().trim();
        LocalDate dato = null;
        try {
            dato = LocalDate.parse(strengDato, formatter);
        }
        catch (DateTimeParseException e) {
        }
        if (dato != null) {
            txfForestillingPladserPåDag.setText("" + Controller.pladserBestiltForForestillingPåDag(forestilling, dato));
            if (kunde != null) {
                ArrayList<Plads> pladser = Controller.getPladserKøbtAfKundePåDato(forestilling,kunde,dato);
                lvwPladser.getItems().setAll(pladser);
            }
        } else {
            alert.setTitle("Beregn statistisk");
            alert.setHeaderText("Forkert Dato");
            alert.setContentText("Du har indtastet en ugyldig dato");
            alert.show();
        }
    }
}
