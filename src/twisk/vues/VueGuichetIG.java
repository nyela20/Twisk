package twisk.vues;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import twisk.mondeIG.GuichetIG;
import twisk.mondeIG.MondeIG;

public class VueGuichetIG extends VueEtapeIG{
    private final HBox boiteClients;
    private final GuichetIG guichetIG;

    /**
     * Constructeur d'une VueGuichetIG
     * @param monde le monde
     * @param guichetIG l'activité
     * @param style et l'identifiant du style d'affichage
     */
    public VueGuichetIG(MondeIG monde, GuichetIG guichetIG, int style){
        super(monde, guichetIG);
        this.guichetIG = guichetIG;
        Label labelTitre = new Label(guichetIG.getNom()+ " : " + guichetIG.getNombreDeJetons() +" jetons\n\n");
        getChildren().add(labelTitre);
        idSelectionne(guichetIG,style,"guichet");
        boiteClients = ajouterHbox(9, style,guichetIG);
        Button boutonAjouterActRes = new Button("Activite Res");
        boutonAjouterActRes.setOnAction(actionEvent -> monde.ajouterSuccesseur("Activite",guichetIG));
        getChildren().add(boutonAjouterActRes);
    }


    @Override
    public void ajouterVueClientIG(VueClientIG vueClientIG) {
        if(guichetIG.CirculationDeGaucheADroite()) {
            Node node = boiteClients.getChildren().get(vueClientIG.getRang()-1);
            HBox hbox = (HBox) node;
            hbox.getChildren().add(vueClientIG);
        }
        if(guichetIG.CirculationDeDroiteAGauche()){
            Node node = boiteClients.getChildren().get(9-vueClientIG.getRang());
            HBox hbox = (HBox) node;
            hbox.getChildren().add(vueClientIG);
        }
    }

    @Override
    public void reagir(){ }

}
