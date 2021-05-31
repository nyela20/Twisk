package twisk.vues;

import javafx.scene.control.Button;
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
     * @param activiteIG l'activité
     * @param style et l'identifiant du style d'affichage
     */
    public VueActiviteIG(MondeIG monde, ActiviteIG activiteIG, int style){
        super(monde, activiteIG);
        Label labelTitre = new Label(activiteIG.getNom() + " : " + activiteIG.getDelai() + " ± " + activiteIG.getEcarttemps() + " temps\n\n");
        this.getChildren().add(labelTitre);
        idSelectionne(activiteIG,style,"activite");
        boiteClients = ajouterHbox(1, style, activiteIG);

        Button buttonActAdd = new Button("activite");
        buttonActAdd.setOnAction(actionEvent -> monde.ajouterSuccesseur("Activite",activiteIG));
        HBox boiteChoixAjouterSucc = new HBox();

        Button buttonGuiAdd = new Button("guichet");
        buttonGuiAdd.setOnAction(actionEvent -> monde.ajouterSuccesseur("Guichet",activiteIG));
        boiteChoixAjouterSucc.getChildren().addAll(buttonActAdd,buttonGuiAdd);

        getChildren().add(boiteChoixAjouterSucc);
    }


    @Override
    public void ajouterVueClientIG(VueClientIG vueClientIG) {
            boiteClients.getChildren().add(vueClientIG);
    }

    @Override
    public void reagir(){ }
}
