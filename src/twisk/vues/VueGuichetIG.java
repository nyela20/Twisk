package twisk.vues;

import javafx.scene.control.Label;
import twisk.monde.GuichetIG;
import twisk.monde.MondeIG;

public class VueGuichetIG extends VueEtapeIG{


    /**
     * Constructeur d'une VueGuichetIG
     * @param monde le monde
     * @param guichet l'activité
     * @param style et l'identifiant du style d'affichage
     */
    public VueGuichetIG(MondeIG monde, GuichetIG guichet, int style){

        super(monde, guichet);
        Label labelTitre = new Label(guichet.getNom()+ " : " + guichet.getNombreDeJetons() +" jetons\n\n");
        this.getChildren().add(labelTitre);
        idSelectionne(guichet,style,"guichet");
        ajouterHbox(9,style,guichet);
    }


    @Override
    public void reagir(){ }

}
