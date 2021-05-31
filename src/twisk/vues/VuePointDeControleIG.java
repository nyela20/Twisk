package twisk.vues;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import twisk.ecouteurs.EcouteurPointDeControle;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.PointDeControleIG;

/**/

public class VuePointDeControleIG extends Circle implements Observateur {

    /**
     * Constructeur d'une VuePointDeControleIG
     * @param mondeIG le mondeIG
     * @param pdc le pointDeControle
     */
    public VuePointDeControleIG(MondeIG mondeIG, PointDeControleIG pdc) {
        double taille;
        this.setOnMouseClicked(new EcouteurPointDeControle(mondeIG,pdc));
        if(pdc.estSelectionne()){
            taille = 10.0f;
        }else{
            taille = 6.0f;
        }
        this.setCenterX(pdc.getx());
        this.setCenterY(pdc.gety());
        this.setRadius(taille);
        this.setFill(Color.ORANGERED);
    }

    @Override
    public void reagir() { }

}
