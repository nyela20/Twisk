package twisk.vues;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import twisk.ecouteurs.EcouteurSetDragOver;
import twisk.ecouteurs.EcouteurDropped;
import twisk.mondeIG.*;
import java.util.Iterator;


public class VueMondeIG extends Pane implements Observateur {
    private final MondeIG mondeIG;

    /**
     * Constructeur d'un VueMondeIG
     * @param mde le monde
     */
    public VueMondeIG(MondeIG mde) {
        mondeIG = mde;
        mondeIG.ajouter(this);
        setOnDragOver(new EcouteurSetDragOver());
        setOnDragDropped(new EcouteurDropped(mondeIG));
    }



    /**
     * La fonction reagir sert à rafraichir l'Ecran
     * apres chaque modification constaté dans le mondeIG
     */
    @Override
    public void reagir() {
        Pane panneau = this;
        Runnable command = () -> {
            System.out.println("le monde se change\n");
            panneau.getChildren().clear();
            panneau.setId("background" + mondeIG.getIdentifiantStyle());

            //DESSIN ARCS
            Iterator<ArcIG> it = mondeIG.iteratorArcIG();
            while (it.hasNext()) {
                ArcIG arc = it.next();
                VueArcIG vuearc = new VueArcIG(mondeIG, arc, mondeIG.getIdentifiantStyle());
                panneau.getChildren().add(vuearc);
            }

            //DESSIN ETAPES
            for (EtapeIG values : mondeIG) {
                VueEtapeIG vueEtapeIG = null;
                if (values.estUneActivite()) {
                    vueEtapeIG = new VueActiviteIG(mondeIG, (ActiviteIG) values, mondeIG.getIdentifiantStyle());
                } else if (values.estUnGuichet()) {
                    vueEtapeIG = new VueGuichetIG(mondeIG, (GuichetIG) values, mondeIG.getIdentifiantStyle());
                }
                assert vueEtapeIG != null;
                panneau.getChildren().add(vueEtapeIG);
                //DESSIN DES CLIENTS
                vueEtapeIG.ajouterVueClientIG(values.getNombreDeClients());
                //DESSIN POINTS DE CONTROLES
                for (Iterator<PointDeControleIG> iter = values.pointDeControleIGIterator(); iter.hasNext(); ) {
                    PointDeControleIG pdc = iter.next();
                    VuePointDeControleIG vuePointDeControleIG = new VuePointDeControleIG(mondeIG, pdc);
                    panneau.getChildren().add(vuePointDeControleIG);
                }
            }
        };
        if(Platform.isFxApplicationThread()){
            command.run();
        }else {
            Platform.runLater(command);
        }
    }
}