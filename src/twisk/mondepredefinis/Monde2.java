package twisk.mondepredefinis;

import twisk.exceptionstwiskIG.TwiskException;
import twisk.mondeIG.ActiviteIG;
import twisk.mondeIG.GuichetIG;
import twisk.mondeIG.MondeIG;

public class Monde2 {

    public Monde2(MondeIG mondeIG){
        /*creer etape*/
        ActiviteIG camp = (ActiviteIG) mondeIG.ajouter("Activite");
        GuichetIG guichetZoo = (GuichetIG) mondeIG.ajouter("Guichet");
        GuichetIG guichetZoo2 = (GuichetIG) mondeIG.ajouter("Guichet");
        ActiviteIG zoo = (ActiviteIG) mondeIG.ajouter("Activite");
        GuichetIG guichetBowling = (GuichetIG) mondeIG.ajouter("Guichet");
        GuichetIG guichetBanque = (GuichetIG) mondeIG.ajouter("Guichet");
        ActiviteIG bowling = (ActiviteIG) mondeIG.ajouter("Activite");
        ActiviteIG banque = (ActiviteIG) mondeIG.ajouter("Activite");

        /*renommer*/
        camp.renommer("camp");
        guichetZoo.renommer("guichetZoo");
        guichetZoo2.renommer("guichetZoo2");
        zoo.renommer("zoo");
        guichetBowling.renommer("guichetBowling");
        bowling.renommer("bowling");
        guichetBanque.renommer("guichetBanque");
        banque.renommer("banque");

        /*deplacer */
        camp.deplacerEtape(100,100);
        guichetZoo.deplacerEtape(325,40);
        guichetZoo2.deplacerEtape(325,200);
        zoo.deplacerEtape(575,100);
        guichetBowling.deplacerEtape(200,360);
        bowling.deplacerEtape(100,520);
        guichetBanque.deplacerEtape(450,360);
        banque.deplacerEtape(575,520);



        /*ajouter arc*/
        try {
            mondeIG.ajouter(camp.getPointDeControle("haut"), guichetZoo.getPointDeControle("gauche"));
            mondeIG.ajouter(camp.getPointDeControle("bas"),guichetZoo2.getPointDeControle("gauche"));
            mondeIG.ajouter(guichetZoo.getPointDeControle("droite"),zoo.getPointDeControle("haut"));
            mondeIG.ajouter(guichetZoo2.getPointDeControle("droite"), zoo.getPointDeControle("bas"));
            mondeIG.ajouter(zoo.getPointDeControle("bas"),guichetBanque.getPointDeControle("droite"));
            mondeIG.ajouter(camp.getPointDeControle("bas"),guichetBowling.getPointDeControle("gauche"));
            mondeIG.ajouter(guichetBowling.getPointDeControle("gauche"),bowling.getPointDeControle("haut"));
            mondeIG.ajouter(guichetBanque.getPointDeControle("droite"),banque.getPointDeControle("haut"));
        }catch (TwiskException exception){
            System.out.println("");
        }

        /*ajouter E/S*/
        mondeIG.setEstSelectionne(camp,true);
        mondeIG.assignerEntree();
        mondeIG.setEstSelectionne(banque,true);
        mondeIG.setEstSelectionne(bowling,true);
        mondeIG.assignerSortie();

        /*assigner nbClients/jetons*/
        mondeIG.setEstSelectionne(guichetBowling,true);
        mondeIG.assignerNombreDeJetonsAEtape(3);
        mondeIG.setEstSelectionne(guichetBanque,true);
        mondeIG.assignerNombreDeJetonsAEtape(3);
    }
}
