package twisk.vues;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import twisk.ecouteurs.EcouterStartDragged;
import twisk.ecouteurs.EcouteurEtapes;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;




public abstract class VueEtapeIG extends VBox implements Observateur {

    /**
     * Constructeur d'une VueEtapeIG
     * @param mde le monde
     * @param etp l'EtapeIG
     */

    public VueEtapeIG(MondeIG mde, EtapeIG etp){
        setPrefWidth(etp.getLargeur());
        setPrefHeight(etp.getHauteur());
        relocate(etp.getPosX(), etp.getPosY());
        setOnDragDetected(new EcouterStartDragged(this, etp.getIdentifiant()));
        setOnMouseClicked(new EcouteurEtapes(mde,etp));
    }


    @Override
    public void reagir(){ }
}
