package twisk.vues;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import twisk.ecouteurs.EcouteurOutilsAssignerNombreDeClients;
import twisk.ecouteurs.EcouteurSimulation;
import twisk.exceptionstwiskIG.ExceptionMondeIG;
import twisk.mondeIG.MondeIG;


public class VueOutils extends TilePane implements Observateur {
    private final MondeIG mondeIG;
    private final Button buttonAjouterActivite = new Button("Activité : + ");
    private final Button buttonAjouterGuichet = new Button("Guichet : + ");
    private final Button buttonDeSimulation = new Button("Start/Stop");
    private final Button buttonSetNombreDeClients = new Button();

    /**
     * Constructeur d'une VueOutils
     * @param mde le monde
     */
    public VueOutils(MondeIG mde){
        mondeIG = mde;
        try {
        mondeIG.ajouter(this);
        buttonAjouterActivite.setOnAction(event -> mondeIG.ajouter("Activite"));
        buttonAjouterGuichet.setOnAction(event -> mondeIG.ajouter("Guichet"));
        buttonDeSimulation.setOnAction(new EcouteurSimulation(mondeIG,buttonDeSimulation));
        buttonSetNombreDeClients.setOnAction(new EcouteurOutilsAssignerNombreDeClients(mondeIG));

        getChildren().add(buttonAjouterActivite);
        getChildren().add(buttonAjouterGuichet);
        getChildren().add(buttonDeSimulation);
        getChildren().add(buttonSetNombreDeClients);
        } catch (ExceptionMondeIG exceptionMondeIG) {
            exceptionMondeIG.printStackTrace();
        }
    }



    @Override
    public void reagir() {
        buttonSetNombreDeClients.setText("modifier nb clients : " + mondeIG.getNombreDeClients());
        this.setId("background"+ mondeIG.getIdentifiantStyle());
        buttonAjouterActivite.setId("buttonActivite"+ mondeIG.getIdentifiantStyle());
        buttonAjouterGuichet.setId("buttonGuichet"+ mondeIG.getIdentifiantStyle());
        buttonDeSimulation.setId("buttonSimulation"+ mondeIG.getIdentifiantStyle());
    }
}
