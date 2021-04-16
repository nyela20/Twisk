package twisk.vues;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import twisk.ecouteurs.EcouteurSetDragOver;
import twisk.ecouteurs.EcouteurDropped;
import twisk.mondeIG.*;

import java.util.Iterator;


public class VueMondeIG extends Pane implements Observateur {
    private final MondeIG monde;

    /**
     * Constructeur d'un VueMondeIG
     * @param mde le monde
     */
    public VueMondeIG(MondeIG mde) {
        monde = mde;
        monde.ajouter(this);
        setOnDragOver(new EcouteurSetDragOver());
        setOnDragDropped(new EcouteurDropped(monde));
    }



    /**
     * La fonciton reagir sert à rafraichir l'Ecran
     * apres chaque modification constaté dans le mondeIG
     */
    @Override
    public void reagir() {
        this.getChildren().clear();
        this.setId("background"+monde.getIdentifiantStyle());

        Iterator<ArcIG> it = monde.iteratorArcIG();
        while (it.hasNext()){
            ArcIG arc = it.next();
            VueArcIG vuearc = new VueArcIG(monde,arc,monde.getIdentifiantStyle());
            this.getChildren().add(vuearc);
        }

        for (EtapeIG values : this.monde) {
            VueEtapeIG vueEtapeIG = null;
            if(values.estUneActivite()) {
                vueEtapeIG = new VueActiviteIG(monde, (ActiviteIG) values,monde.getIdentifiantStyle());
            }else if(values.estUnGuichet()){
                vueEtapeIG = new VueGuichetIG(monde, (GuichetIG) values, monde.getIdentifiantStyle());
            }
            this.getChildren().add(vueEtapeIG);
            for (PointDeControleIG pdc : values) {
                VuePointDeControleIG vuePointDeControleIG = new VuePointDeControleIG(monde,pdc);
                this.getChildren().add(vuePointDeControleIG);
            }
        }
    }
}