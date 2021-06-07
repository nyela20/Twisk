package twisk.vues;

import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import twisk.ecouteurs.*;
import twisk.mondeIG.MondeIG;

public class VueMenu extends MenuBar implements Observateur {

    /**
     * Constructeur d'une VueMenu
     * @param mondeIG le mondeIG
     */
    public VueMenu(MondeIG mondeIG) {
        super();

        mondeIG.ajouter(this);

        Menu Monde = new Menu("Monde");
        Menu Fichier = new Menu("Fichier");
        Menu Edition = new Menu("Edition");
        Menu Parametre = new Menu("Paramètres");
        Menu Style = new Menu("Style");
        Menu Mondepredefinis = new Menu("Monde prédefinis");
        Menu Loi = new Menu("Loi d'arrivée");


        /*--ENTREE--*/
        MenuItem Entree = new MenuItem("Entrée (Crtl + Maj + E)");
        Entree.setOnAction(new EcouteurMenuMondeEntree(mondeIG));
        ajouterGraphic(Entree,"twisk/ressources/images/menu/logoentree.png");
        Monde.getItems().add(Entree);

        /*--SORTIE--*/
        MenuItem Sortie = new MenuItem("Sortie (Ctrl + Maj + S)");
        Sortie.setOnAction(new EcouteurMenuMondeSortie(mondeIG));
        ajouterGraphic(Sortie,"twisk/ressources/images/menu/logosortie.png");
        Monde.getItems().add(Sortie);


        /*MONDE-PREDEFINIE-1*/
        MenuItem Monde1 = new MenuItem("Monde 1");
        Monde1.setOnAction(actionEvent -> mondeIG.ajouterMondePredefini1());
        ajouterGraphic(Monde1,"twisk/ressources/images/menu/monde.png");
        Mondepredefinis.getItems().add(Monde1);


        /*MONDE-PREDEFINIE-2*/
        MenuItem Monde2 = new MenuItem("Monde 2");
        Monde2.setOnAction(actionEvent -> mondeIG.ajouterMondePredefini2());
        ajouterGraphic(Monde2,"twisk/ressources/images/menu/monde.png");
        Mondepredefinis.getItems().add(Monde2);

        /*MONDE-PREDEFINIE-3*/
        MenuItem Monde3 = new MenuItem("Monde 3");
        Monde3.setOnAction(actionEvent -> mondeIG.ajouterMondePredefini3());
        ajouterGraphic(Monde3,"twisk/ressources/images/menu/monde.png");
        Mondepredefinis.getItems().add(Monde3);

        /*--NOUVEAU--*/
        MenuItem nouveau = new MenuItem("Nouveau (Ctrl + N)");
        nouveau.setOnAction(actionEvent -> mondeIG.nouveauMonde());
        ajouterGraphic(nouveau,"twisk/ressources/images/menu/nouveau.png");
        Fichier.getItems().add(nouveau);

        /*--DELAI--*/
       MenuItem Delai = new MenuItem("Délai (Ctrl + D)");
       Delai.setOnAction(new EcouteurMenuDelai(mondeIG));
       ajouterGraphic(Delai,"twisk/ressources/images/menu/temp.png");
       Parametre.getItems().add(Delai);

        /*--ECART-TEMPS--*/
        MenuItem EcartTemps = new MenuItem("EcartTemps (Ctrl + T)");
        EcartTemps.setOnAction(new EcouteurMenuEcartTemps(mondeIG));
        ajouterGraphic(EcartTemps,"twisk/ressources/images/menu/temp.png");
        Parametre.getItems().add(EcartTemps);

        /*--JETONS-GUICHETS-*/
        MenuItem JetonsGuichet = new MenuItem("Nombre de Jeton(s) (Ctrl + J)");
        JetonsGuichet.setOnAction(new EcouteurMenuNombreDeJetons(mondeIG));
        ajouterGraphic(JetonsGuichet,"twisk/ressources/images/menu/jeton.png");
        Parametre.getItems().add(JetonsGuichet);

        /*CHARGER*/
        MenuItem Ouvrir = new MenuItem("Ouvrir... (Ctrl + O)");
        Ouvrir.setOnAction(new EcouteurOuvrirMonde(mondeIG));
        ajouterGraphic(Ouvrir,"twisk/ressources/images/menu/ouvrir.png");
        Fichier.getItems().add(Ouvrir);

        /*-SAUVEGARDER-*/
        MenuItem Sauvegarder = new MenuItem("Sauvegarder (Ctrl + Q)");
        Sauvegarder.setOnAction(new EcouteurSauvegarderMonde(mondeIG));
        ajouterGraphic(Sauvegarder,"twisk/ressources/images/menu/save.png");
        Fichier.getItems().add(Sauvegarder);

        /*--QUITTER--*/
        MenuItem Quitter = new MenuItem("Quitter (Ctrl + Esc)");
        Quitter.setOnAction(new EcouteurMenuFichierQuitter(mondeIG));
        ajouterGraphic(Quitter,"twisk/ressources/images/menu/quitter.png");
        Fichier.getItems().add(Quitter);


        /*--SUPPRIMER--*/
        MenuItem Supprimer = new MenuItem("Supprimer (Suppr)");
        Supprimer.setOnAction(new EcouteurMenuSupprimerEtapeArc(mondeIG));
        ajouterGraphic(Supprimer,"twisk/ressources/images/menu/supprimer.png");
        Edition.getItems().add(Supprimer);

        /*--RENOMMER--*/
        MenuItem Renommer = new MenuItem("Renommer (Ctrl + R)");
        Renommer.setOnAction(new EcouteurMenuRenommer(mondeIG));
        ajouterGraphic(Renommer,"twisk/ressources/images/menu/renommer.png");
        Edition.getItems().add(Renommer);


        /*--EFFACER SELECTION--*/
        MenuItem EffacerSelection = new MenuItem("Effacer la sélection (Ctrl + F)");
        EffacerSelection.setOnAction(new EcouteurMenuEffacerLaSelection(mondeIG));
        ajouterGraphic(EffacerSelection,"twisk/ressources/images/menu/effacer.png");
        Edition.getItems().add(EffacerSelection);

        ImageView imageView = new ImageView(new Image("twisk/ressources/images/menu/casecoche.png"));
        MenuItem LoiUniforme = new MenuItem("Loi uniforme",imageView);
        MenuItem LoiPoisson = new MenuItem("Loi poisson");
        MenuItem LoiNormale = new MenuItem("Loi Normale");

        /*--LOI UNIFORME--*/
        LoiUniforme.setOnAction(actionEvent -> {
            initGraphics(LoiNormale,LoiPoisson,LoiUniforme);
            mondeIG.initLoi(); mondeIG.setSuitLoiUniforme(true);
            LoiUniforme.setGraphic(imageView);
        });
        Loi.getItems().add(LoiUniforme);

        /*--LOI POISSON--*/
        LoiPoisson.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.getDialogPane().setMinHeight(200);
            alert.setContentText("Avec la loi poisson, il peut PARFOIS arriver que l'affichage de l'interface soit mal mis à jour ");
            alert.showAndWait();
            initGraphics(LoiNormale,LoiPoisson,LoiUniforme);
            mondeIG.initLoi(); mondeIG.setSuitLoiPoisson(true);
            LoiPoisson.setGraphic(imageView);
        });
        Loi.getItems().add(LoiPoisson);

        /*--LOI NORMALE--*/
        LoiNormale.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.getDialogPane().setMinHeight(200);
            alert.setContentText("Avec la loi normale, il peut PARFOIS arriver que l'affichage de l'interface soit mal mis à jour ");
            alert.showAndWait();
            initGraphics(LoiNormale,LoiPoisson,LoiUniforme);
            mondeIG.initLoi(); mondeIG.setSuitLoiNormale(true);
            LoiNormale.setGraphic(imageView);
        });
        Loi.getItems().add(LoiNormale);

        /*--MENU STYLE PERSONNALISE--*/
        MenuItem StyleParDefault = new MenuItem("Par défaut mode");
        StyleParDefault.setOnAction(new EcouteurMenuStyle(mondeIG,0));
        MenuItem Style1 = new MenuItem("Light mode");
        Style1.setOnAction(new EcouteurMenuStyle(mondeIG,1));
        MenuItem Style4 = new MenuItem("Dark mode");
        Style4.setOnAction(new EcouteurMenuStyle(mondeIG,4));
        MenuItem Style3 = new MenuItem("Fruit mode");
        Style3.setOnAction(new EcouteurMenuStyle(mondeIG,3));

        Style.getItems().addAll(StyleParDefault,Style1,Style4,Style3);


        this.getMenus().add(Fichier);
        this.getMenus().add(Edition);
        this.getMenus().add(Monde);
        this.getMenus().add(Mondepredefinis);
        this.getMenus().add(Parametre);
        this.getMenus().add(Loi);
        this.getMenus().add(Style);
        this.setPrefWidth(750);
        this.setPrefHeight(25);
    }

    public void initGraphics(MenuItem ... menuItems){
        for(MenuItem menuItem : menuItems){
            menuItem.setGraphic(new Text(""));
        }
    }

    public void ajouterGraphic(MenuItem menuItem,String path){
        menuItem.setGraphic(new ImageView(new Image(path)));
    }



    @Override
    public void reagir() {

    }
}
