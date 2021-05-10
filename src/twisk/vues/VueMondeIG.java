package twisk.vues;

import javafx.scene.layout.Pane;
import twisk.ecouteurs.EcouteurSetDragOver;
import twisk.ecouteurs.EcouteurDropped;
import twisk.exceptionstwiskIG.ExceptionMondeIG;
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
     * La fonciton reagir sert à rafraichir l'Ecran
     * apres chaque modification constaté dans le mondeIG
     */
    @Override
    public void reagir() {
        this.getChildren().clear();
        this.setId("background"+ mondeIG.getIdentifiantStyle());

        //DESSIN ARCS
        Iterator<ArcIG> it = mondeIG.iteratorArcIG();
        while (it.hasNext()){
            ArcIG arc = it.next();
            VueArcIG vuearc = new VueArcIG(mondeIG,arc, mondeIG.getIdentifiantStyle());
            this.getChildren().add(vuearc);
        }


        //DESSIN ETAPES
        for (EtapeIG values : mondeIG){
            VueEtapeIG vueEtapeIG = null;
            if(values.estUneActivite()) {
                vueEtapeIG = new VueActiviteIG(mondeIG, (ActiviteIG) values, mondeIG.getIdentifiantStyle());
            }else if(values.estUnGuichet()){
                vueEtapeIG = new VueGuichetIG(mondeIG, (GuichetIG) values, mondeIG.getIdentifiantStyle());
            }
            this.getChildren().add(vueEtapeIG);
            //DESSIN DES CLIENTS
            Iterator<Client> iterateurClients = mondeIG.iteratorClients();
            while (iterateurClients.hasNext()) {
                Client client = iterateurClients.next();
                if(client.estDans(values.getNom())){
                    VueClientIG vueClientIG = new VueClientIG(client);
                    vueEtapeIG.getChildren().add(vueClientIG);
                }
            }
            //DESSIN POINTS DE CONTROLES
            for (Iterator<PointDeControleIG> iter = values.pointDeControleIGIterator(); iter.hasNext(); ) {
                PointDeControleIG pdc = iter.next();
                VuePointDeControleIG vuePointDeControleIG = new VuePointDeControleIG(mondeIG,pdc);
                this.getChildren().add(vuePointDeControleIG);
            }
        }
    }
}