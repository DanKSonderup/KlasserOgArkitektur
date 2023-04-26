package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import model.Forestilling;
import model.Kunde;

import java.awt.*;

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
    private final TextField txfKundePladserPåDag = new TextField();


    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);
        Label lblDatoPladserPåDag = new Label("Indtast dato");
        lblDatoPladserPåDag.setAlignment(Pos.CENTER);
        Label lblInfoPladserPåDag = new Label("Antal pladser solgt for " + forestilling.getNavn());
        pane.add(lblDatoPladserPåDag,0,0);
        pane.add(txfDato,0,1);
        pane.add(lblInfoPladserPåDag,0,3);
        pane.add(txfForestillingPladserPåDag,0,4);
        txfForestillingPladserPåDag.setEditable(false);

        Label lblInfoKundePladserPåDag = new Label("Antal pladser købt af " + kunde.getNavn());
        pane.add(lblInfoKundePladserPåDag ,0,5);
        pane.add(txfKundePladserPåDag,0,6);

    }
}
