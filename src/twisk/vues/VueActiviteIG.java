package twisk.vues;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import twisk.mondeIG.ActiviteIG;
import twisk.mondeIG.MondeIG;


/**/
public class VueActiviteIG extends VueEtapeIG implements Observateur {


    /**
     * Constructeur d'une VueActiviteIG
     * @param monde le monde
     * @param activite l'activité
     * @param style et l'identifiant du style d'affichage
     */
    public VueActiviteIG(MondeIG monde, ActiviteIG activite, int style){

        super(monde, activite);

        Label labelTitre = new Label(activite.getNom() + " : " + activite.getDelai() + " ± " + activite.getEcarttemps() + " temps\n\n");

        if(activite.estSelectionne()) {
            this.setId("activite"+style+"_selected");
        }else{
            this.setId("activite"+style+"_not_selected");
        }

        if(activite.estUneEntree()){
            this.getChildren().add(new ImageView(new Image("twisk/ressources/images/logoentree.jpg", 20, 20, true, true)));
        }

        if(activite.estUneSortie()){
            this.getChildren().add(new ImageView(new Image("twisk/ressources/images/logosortie.jpg", 20, 20, true, true)));
        }

        HBox caseClients = new HBox();
        caseClients.setId("activite"+style+"_hbox_clients");
        this.getChildren().add(labelTitre);
        this.getChildren().add(caseClients);
    }


    @Override
    public void reagir(){ }

}
