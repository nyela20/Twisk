package twisk.vues;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import twisk.ecouteurs.EcouteurPointDeControle;
import twisk.monde.MondeIG;
import twisk.monde.PointDeControleIG;

/**/

public class VuePointDeControleIG extends Circle implements Observateur {

    /**
     * Constructeur d'une VuePointDeControleIG
     * @param monde le monde
     * @param pdc le pointDeControle
     */
    public VuePointDeControleIG(MondeIG monde, PointDeControleIG pdc) {
        double taille;
        this.setOnMouseClicked(new EcouteurPointDeControle(monde,pdc));
        if(pdc.estSelectionne()){
            taille = 8.0f;
        }else{
            taille = 4.0f;
        }
        this.setCenterX(pdc.getx());
        this.setCenterY(pdc.gety());
        this.setRadius(taille);
        this.setFill(Color.RED);
    }

    @Override
    public void reagir() { }

}
