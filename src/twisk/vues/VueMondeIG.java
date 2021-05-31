package twisk.vues;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import twisk.ecouteurs.EcouteurSetDragOver;
import twisk.ecouteurs.EcouteurDropped;
import twisk.mondeIG.*;
import twisk.simulation.Client;
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
                try {
                    for (Iterator<Client> iter = mondeIG.iteratorClient(); iter.hasNext(); ) {
                        Client client = iter.next();
                        if (client.estDans(values.getNom())) {
                            VueClientIG vueClientIG = new VueClientIG(client);
                            vueEtapeIG.ajouterVueClientIG(vueClientIG);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //DESSIN POINTS DE CONTROLES
                if(mondeIG.estModeCreation()) {
                    for (Iterator<PointDeControleIG> iter = values.pointDeControleIGIterator(); iter.hasNext(); ) {
                        PointDeControleIG pdc = iter.next();
                        VuePointDeControleIG vuePointDeControleIG = new VuePointDeControleIG(mondeIG, pdc);
                        panneau.getChildren().add(vuePointDeControleIG);
                    }
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