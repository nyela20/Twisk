package twisk.vues;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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


        /*--ENTREE--*/
        MenuItem Entree = new MenuItem("Entrée (Crtl + Maj + E)");
        Entree.setOnAction(new EcouteurMenuMondeEntree(mondeIG));
        Monde.getItems().add(Entree);

        /*--SORTIE--*/
        MenuItem Sortie = new MenuItem("Sortie (Ctrl + Maj + S)");
        Sortie.setOnAction(new EcouteurMenuMondeSortie(mondeIG));
        Monde.getItems().add(Sortie);


        /*MONDE-PREDEFINIE-1*/
        MenuItem Monde1 = new MenuItem("Monde 1");
        Monde1.setOnAction(actionEvent -> mondeIG.ajouterMondePredefini1());
        Mondepredefinis.getItems().add(Monde1);


        /*MONDE-PREDEFINIE-2*/
        MenuItem Monde2 = new MenuItem("Monde 2");
        Monde2.setOnAction(actionEvent -> mondeIG.ajouterMondePredefini2());
        Mondepredefinis.getItems().add(Monde2);

        /*MONDE-PREDEFINIE-3*/
        MenuItem Monde3 = new MenuItem("Monde 3");
        Monde3.setOnAction(actionEvent -> mondeIG.ajouterMondePredefini3());
        Mondepredefinis.getItems().add(Monde3);

        /*--NOUVEAU--*/
        MenuItem ToutSupprimer = new MenuItem("Nouveau (Ctrl + N)");
        ToutSupprimer.setOnAction(actionEvent -> mondeIG.toutSupprimer());
        Fichier.getItems().add(ToutSupprimer);

        /*--DELAI--*/
       MenuItem Delai = new MenuItem("Délai (Ctrl + D)");
       Delai.setOnAction(new EcouteurMenuDelai(mondeIG));
       Parametre.getItems().add(Delai);

        /*--ECART-TEMPS--*/
        MenuItem EcartTemps = new MenuItem("EcartTemps (Ctrl + T)");
        EcartTemps.setOnAction(new EcouteurMenuEcartTemps(mondeIG));
        Parametre.getItems().add(EcartTemps);

        /*--JETONS-GUICHETS-*/
        MenuItem JetonsGuichet = new MenuItem("Nombre de Jeton(s) (Ctrl + J)");
        JetonsGuichet.setOnAction(new EcouteurMenuNombreDeJetons(mondeIG));
        Parametre.getItems().add(JetonsGuichet);

        /*CHARGER*/
        MenuItem Ouvrir = new MenuItem("Ouvrir... (Ctrl + O)");
        Ouvrir.setOnAction(new EcouteurOuvrirMonde(mondeIG));
        Fichier.getItems().add(Ouvrir);

        /*-SAUVEGARDER-*/
        MenuItem Sauvegarder = new MenuItem("Sauvegarder (Ctrl + Q)");
        Sauvegarder.setOnAction(new EcouteurSauvegarderMonde(mondeIG));
        Fichier.getItems().add(Sauvegarder);

        /*--QUITTER--*/
        MenuItem Quitter = new MenuItem("Quitter (Ctrl + Esc)");
        Quitter.setOnAction(new EcouteurMenuFichierQuitter(mondeIG));
        Fichier.getItems().add(Quitter);


        /*--SUPPRIMER--*/
        MenuItem Supprimer = new MenuItem("Supprimer (Suppr)");
        Supprimer.setOnAction(new EcouteurMenuSupprimerEtapeArc(mondeIG));
        Edition.getItems().add(Supprimer);

        /*--RENOMMER--*/
        MenuItem Renommer = new MenuItem("Renommer (Ctrl + R)");
        Renommer.setOnAction(new EcouteurMenuRenommer(mondeIG));
        Edition.getItems().add(Renommer);


        /*--EFFACER SELECTION--*/
        MenuItem EffacerSelection = new MenuItem("Effacer la sélection (Ctrl + F)");
        EffacerSelection.setOnAction(new EcouteurMenuEffacerLaSelection(mondeIG));
        Edition.getItems().add(EffacerSelection);

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
        this.getMenus().add(Style);
        this.setPrefWidth(750);
        this.setPrefHeight(25);
    }

    @Override
    public void reagir() {

    }
}
