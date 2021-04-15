package twisk.vues;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import twisk.mondeIG.GuichetIG;
import twisk.mondeIG.MondeIG;

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

        if(guichet.estSelectionne()) {
            this.setId("guichet"+style+"_selected");
        }else{
            this.setId("guichet"+style+"_not_selected");
        }

        if(guichet.estUneEntree()){
            this.getChildren().add(new ImageView(new Image("twisk/ressources/images/logoentree.jpg", 20, 20, true, true)));
        }

        if(guichet.estUneSortie()){
            this.getChildren().add(new ImageView(new Image("twisk/ressources/images/logosortie.jpg", 20, 20, true, true)));
        }

        HBox caseClients = new HBox();
        caseClients.setId("guichet"+style+"_hbox_clients");
        this.getChildren().add(labelTitre);
        this.getChildren().add(caseClients);
    }


    @Override
    public void reagir(){ }
}
