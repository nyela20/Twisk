package twisk.vues;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import twisk.ecouteurs.EcouteurPointDeControle;
import twisk.mondeIG.MondeIG;
import twisk.simulation.Client;

public class VueClientIG extends Circle implements Observateur{

    public VueClientIG (Client client){
        double taille = 5;
        this.setRadius(taille);
        this.setFill(Color.ORANGE);
    }

    @Override
    public void reagir() {

    }
}
