package twisk.vues;

import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import twisk.mondeIG.MondeIG;


public class VueOutils extends TilePane implements Observateur {
    private final MondeIG monde;
    private final Button buttonAjouterActivite = new Button("+");
    private final Button buttonAjouterGuichet = new Button("+");

    /**
     * Constructueur d'une VueOutils
     * @param mde le monde
     */
    public VueOutils(MondeIG mde){
        monde = mde;
        monde.ajouter(this);
        buttonAjouterActivite.setOnAction(event -> monde.ajouter("Activite"));
        buttonAjouterGuichet.setOnAction(event -> monde.ajouter("Guichet"));
        getChildren().add(buttonAjouterActivite);
        getChildren().add(buttonAjouterGuichet);
    }

    @Override
    public void reagir() {
        buttonAjouterActivite.setId("buttonActivite"+monde.getIdentifiantStyle());
        buttonAjouterGuichet.setId("buttonGuichet"+monde.getIdentifiantStyle());
    }
}
