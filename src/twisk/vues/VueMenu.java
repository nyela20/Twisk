package twisk.vues;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import twisk.ecouteurs.*;
import twisk.mondeIG.MondeIG;

public class VueMenu extends MenuBar implements Observateur {

    /**
     * Constructeur d'une VueMenu
     * @param monde le monde
     */
    public VueMenu(MondeIG monde) {
        super();

        monde.ajouter(this);

        Menu Monde = new Menu("Monde");
        Menu Fichier = new Menu("Fichier");
        Menu Edition = new Menu("Edition");
        Menu Parametre = new Menu("Paramètres");
        Menu Style = new Menu("Style");

        /*--ENTREE--*/
        MenuItem Entree = new MenuItem("Entrée (Crtl+Maj+E)");
        Entree.setOnAction(new EcouteurMenuMondeEntree(monde));
        Monde.getItems().add(Entree);

        /*--SORTIE--*/
        MenuItem Sortie = new MenuItem("Sortie (Ctrl+Maj+S)");
        Sortie.setOnAction(new EcouteurMenuMondeSortie(monde));
        Monde.getItems().add(Sortie);

        /*--DELAI--*/
       MenuItem Delai = new MenuItem("Délai (Ctrl+D)");
       Delai.setOnAction(new EcouteurMenuDelai(monde));
       Parametre.getItems().add(Delai);

        /*--ECART-TEMPS--*/
        MenuItem EcartTemps = new MenuItem("EcartTemps (Ctrl+T)");
        EcartTemps.setOnAction(new EcouteurMenuEcartTemps(monde));
        Parametre.getItems().add(EcartTemps);

        /*--JETONS-GUICHETS-*/
        MenuItem JetonsGuichet = new MenuItem("Nombre de Jeton(s) (Ctrl+J)");
        JetonsGuichet.setOnAction(new EcouteurMenuNombreDeJetons(monde));
        Parametre.getItems().add(JetonsGuichet);

        /*--QUITTER--*/
        MenuItem Quitter = new MenuItem("Quitter (Ctrl+Maj+Esc)");
        Quitter.setOnAction(new EcouteurMenuFichierQuitter());
        Fichier.getItems().add(Quitter);

        /*--SUPPRIMER--*/
        MenuItem Supprimer = new MenuItem("Supprimer (Suppr)");
        Supprimer.setOnAction(new EcouteurMenuSupprimerEtapeArc(monde));
        Edition.getItems().add(Supprimer);

        /*--RENOMMER--*/
        MenuItem Renommer = new MenuItem("Renommer (Ctrl+R)");
        Renommer.setOnAction(new EcouteurMenuRenommer(monde));
        Edition.getItems().add(Renommer);

        /*--EFFACER SELECTION--*/
        MenuItem EffacerSelection = new MenuItem("Effacer la sélection (Ctrl+F)");
        EffacerSelection.setOnAction(new EcouteurMenuEffacerLaSelection(monde));
        Edition.getItems().add(EffacerSelection);

        /*--MENU STYLE PERSONNALISE--*/
        MenuItem StyleParDefault = new MenuItem("Par défaut mode");
        StyleParDefault.setOnAction(new EcouteurMenuStyle(monde,0));
        MenuItem Style1 = new MenuItem("Light mode");
        Style1.setOnAction(new EcouteurMenuStyle(monde,1));
        MenuItem Style4 = new MenuItem("Dark mode");
        Style4.setOnAction(new EcouteurMenuStyle(monde,4));
      //  MenuItem Style2 = new MenuItem("Style 2");
      //  Style2.setOnAction(new EcouteurMenuStyle(monde,2));
        MenuItem Style3 = new MenuItem("Fruit mode");
        Style3.setOnAction(new EcouteurMenuStyle(monde,3));

        Style.getItems().addAll(StyleParDefault,Style1,Style4,Style3);


        this.getMenus().add(Fichier);
        this.getMenus().add(Edition);
        this.getMenus().add(Monde);
        this.getMenus().add(Parametre);
        this.getMenus().add(Style);
        this.setPrefWidth(750);
        this.setPrefHeight(25);
    }

    @Override
    public void reagir() { }
}