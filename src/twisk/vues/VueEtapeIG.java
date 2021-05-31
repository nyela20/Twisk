package twisk.vues;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import twisk.ecouteurs.EcouteurStartDragged;
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
        setOnDragDetected(new EcouteurStartDragged(this, etp.getIdentifiant()));
        setOnMouseClicked(new EcouteurEtapes(mde,etp));
        idEntreeSortie(etp);
    }


    /**
     * ajouter un Hbox dans la VueEtape
     * @param nombreHbox le nombre de Hbox à ajouter
     * @param style l'identifiant de style de la vue
     * @param etape l'Etape
     */
    public HBox ajouterHbox(int nombreHbox,int style,EtapeIG etape){
        HBox caseClients = new HBox();
        if(etape.estUnGuichet()) {
            for (int i = 0; i < nombreHbox ; i++) {
                HBox caseClients2 = new HBox();
                caseClients2.setId("guichet" + style + "_hbox_clients");
                caseClients.getChildren().add(caseClients2);
            }
        }
        if(etape.estUneActivite()){
            caseClients.setId("activite" + style + "_hbox_clients");
        }
        this.getChildren().add(caseClients);
        return caseClients;
    }

    /**
     * la fonction assigner un Style à le vue Etape
     * en vérifiant si oui/non l'Etape est Séléctionné
     * @param etape L'etape
     * @param style l'Identifiant le style
     * @param type le type
     */
    public void idSelectionne(EtapeIG etape,int style, String type){
        assert(type.equals("activite") || type.equals("guichet")) : "erreur type inconnu";
        if(etape.estSelectionne()) {
            this.setId(type+style+"_selected");
        }else{
            this.setId(type+style+"_not_selected");
        }
    }

    /**
     * la fonction sert à ajouter/enlever le logo Entree/Sortie
     * si oui/non l'Etape est une Entree nor une Sortie
     * @param etape l'Etape
     */
    public void idEntreeSortie(EtapeIG etape){
        HBox boiteLogo = new HBox();
        if(etape.estUneEntree()){
            boiteLogo.getChildren().add(new ImageView(new Image("twisk/ressources/images/logoentree.png", 20, 20, true, true)));
        }
        if(etape.estUneSortie()){
            boiteLogo.getChildren().add(new ImageView(new Image("twisk/ressources/images/logosortie.png", 20, 20, true, true)));
        }
        getChildren().add(boiteLogo);
    }

    /**
     * La fonction ajoute des VueClientsIG dans le composant
     * @param vueClientIG  composant client à ajouter
     */
    public abstract void ajouterVueClientIG(VueClientIG vueClientIG);


    @Override
    public void reagir(){ }

}
