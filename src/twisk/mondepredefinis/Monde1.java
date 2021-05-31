package twisk.mondepredefinis;

import twisk.exceptionstwiskIG.TwiskException;
import twisk.mondeIG.ActiviteIG;
import twisk.mondeIG.GuichetIG;
import twisk.mondeIG.MondeIG;

public class Monde1 {

    public Monde1(MondeIG mondeIG){
        /*creer etape*/
        ActiviteIG parc = (ActiviteIG) mondeIG.ajouter("Activite");
        GuichetIG guichetPiscine = (GuichetIG) mondeIG.ajouter("Guichet");
        ActiviteIG piscine = (ActiviteIG) mondeIG.ajouter("Activite");
        ActiviteIG parc2 = (ActiviteIG) mondeIG.ajouter("Activite");
        GuichetIG guichetCinema = (GuichetIG) mondeIG.ajouter("Guichet");
        ActiviteIG cinema = (ActiviteIG) mondeIG.ajouter("Activite");
        GuichetIG guichetArcade = (GuichetIG) mondeIG.ajouter("Guichet");
        ActiviteIG arcade = (ActiviteIG) mondeIG.ajouter("Activite");

        /*renommer*/
        parc.renommer("parc");
        guichetPiscine.renommer("guichetPiscine");
        piscine.renommer("piscine");
        parc2.renommer("parc2");
        guichetCinema.renommer("guichetCinema");
        cinema.renommer("cinema");
        guichetArcade.renommer("guichetArcade");
        arcade.renommer("arcade");

        /*deplacer */
        parc.deplacerEtape(100,100);
        guichetPiscine.deplacerEtape(325,120);
        piscine.deplacerEtape(575,100);
        parc2.deplacerEtape(575,300);
        guichetCinema.deplacerEtape(325,320);
        cinema.deplacerEtape(100,300);
        guichetArcade.deplacerEtape(325,520);
        arcade.deplacerEtape(575,500);



        /*ajouter arc*/
        try {
            mondeIG.ajouter(parc.getPointDeControle("droite"), guichetPiscine.getPointDeControle("gauche"));
            mondeIG.ajouter(guichetPiscine.getPointDeControle("droite"),piscine.getPointDeControle("gauche"));
            mondeIG.ajouter(piscine.getPointDeControle("bas"),parc2.getPointDeControle("haut"));
            mondeIG.ajouter(parc2.getPointDeControle("gauche"),guichetCinema.getPointDeControle("droite"));
            mondeIG.ajouter(guichetCinema.getPointDeControle("gauche"),cinema.getPointDeControle("droite"));
            mondeIG.ajouter(cinema.getPointDeControle("bas"),guichetArcade.getPointDeControle("gauche"));
            mondeIG.ajouter(guichetArcade.getPointDeControle("droite"),arcade.getPointDeControle("gauche"));
        }catch (TwiskException exception){
            System.out.println(exception.getMessage());
        }

        /*ajouter E/S*/
        mondeIG.setEstSelectionne(parc,true);
        mondeIG.assignerEntree();
        mondeIG.setEstSelectionne(arcade,true);
        mondeIG.assignerSortie();

        /*assigner nbClients/jetons*/
        mondeIG.setEstSelectionne(guichetCinema,true);
        mondeIG.assignerNombreDeJetonsAEtape(4);
    }
}
