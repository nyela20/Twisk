package twisk.vues;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import twisk.mondeIG.ActiviteIG;
import twisk.mondeIG.MondeIG;


/**/
public class VueActiviteIG extends VueEtapeIG implements Observateur {
    private final HBox boiteClients;

    /**
     * Constructeur d'une VueActiviteIG
     * @param monde le monde
     * @param activite l'activité
     * @param style et l'identifiant du style d'affichage
     */
    public VueActiviteIG(MondeIG monde, ActiviteIG activite, int style){
        super(monde, activite);
        Label labelTitre = new Label(activite.getNom() + " : " + activite.getDelai() + " ± " + activite.getEcarttemps() + " temps\n\n");
        this.getChildren().add(labelTitre);
        idSelectionne(activite,style,"activite");
        boiteClients = ajouterHbox(1, style, activite);
    }


    @Override
    public void ajouterVueClientIG(int nbClients) {
        for(int i =0; i < nbClients ; i++ ) {
            boiteClients.getChildren().add(new VueClientIG());
        }
    }

    @Override
    public void reagir(){ }
}
