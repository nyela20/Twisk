package twisk.teststwiskig;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.exceptionstwiskig.*;
import twisk.mondeIG.*;
import twisk.outils.FabriqueIdentifiant;


public class PointDeControleIGTest {

    MondeIG monde;

    PointDeControleIG pdcUn;
    PointDeControleIG pdcDeux;
    PointDeControleIG pdcTrois;
    PointDeControleIG pdcQuatre;

    EtapeIG Tonnerre_de_Zeus;
    EtapeIG Goudurix;


    @BeforeEach
    void setUp(){
        monde = new MondeIG();
        String idf = FabriqueIdentifiant.getInstance().getIdentifiant();
        Tonnerre_de_Zeus = new ActiviteIG("Tonnerre_de_Zeus",idf,100,75);
        pdcUn = new PointDeControleIG(Tonnerre_de_Zeus, "haut");
        pdcDeux = new PointDeControleIG(Tonnerre_de_Zeus, "bas");

        idf = FabriqueIdentifiant.getInstance().getIdentifiant();
        Goudurix = new ActiviteIG("Goudurix",idf,100,75);
        pdcTrois = new PointDeControleIG(Goudurix, "gauche");
        pdcQuatre = new PointDeControleIG(Goudurix, "droite");
    }


    @Test
    void estSurLaMemeEtapeQue(){
        assert(pdcUn.estSurLaMemeEtapeQue(pdcDeux)) : "Erreur valeur incorrecte pour 2 pdcs  sur la même étape";
        assert(!pdcUn.estSurLaMemeEtapeQue(pdcTrois)) : "Erreur valeur incorrecte pour 2 pdcs pas sur la même etape";
    }

    @Test
    void estSelectionne(){
        assert(!pdcUn.estSelectionne()) : "A l'initialisation le pdc n'est pas censé être seléctionné";
        pdcUn.setEstSelectionne(true);
        assert(pdcUn.estSelectionne()) : "Le pdc est pas censé être seléctionné";
        try {
            monde.ajouter(pdcUn, pdcQuatre);
        }catch(ExceptionsInvaliditeSurLesArcs e){
            e.printStackTrace();
        }
        assert(!pdcUn.estSelectionne()) : "Après avoir créer un arc ses pdc ne sont plus censé seléctionné";
        assert(!pdcQuatre.estSelectionne()) : "Après avoir créer un arc ses pdc ne sont plus censé seléctionné";
    }

    @Test
    void assignerPosition(){
        pdcDeux.assignerPosition("haut");
        int largeur = Tonnerre_de_Zeus.getLargeur()/2;
        int hauteur = Tonnerre_de_Zeus.getHauteur()/2;
        assert(pdcDeux.getx() == Tonnerre_de_Zeus.getPosX()+ largeur) : "Le point de contrôle ne modifie pas les coordonnées X du points haut";
        assert(pdcDeux.gety() == Tonnerre_de_Zeus.getPosY()) : "Le point de contrôle ne modifie pas les coordonnées Y du points haut";
        pdcDeux.assignerPosition("bas");
        assert(pdcDeux.getx() == Tonnerre_de_Zeus.getPosX()+ largeur) : "Le point de contrôle ne modifie pas les coordonnées X du points bas";
        assert(pdcDeux.gety() == Tonnerre_de_Zeus.getPosY() + Tonnerre_de_Zeus.getHauteur()) : "Le point de contrôle ne modifie pas les coordonnées Y du points bas";
        pdcDeux.assignerPosition("gauche");
        assert(pdcDeux.getx() == Tonnerre_de_Zeus.getPosX()) : "Le point de contrôle ne modifie pas les coordonnées X du points gauche";
        assert(pdcDeux.gety() == Tonnerre_de_Zeus.getPosY() + hauteur): "Le point de contrôle ne modifie pas les coordonnées Y du points gauche";
        pdcDeux.assignerPosition("droite");
        assert(pdcDeux.getx() == Tonnerre_de_Zeus.getPosX() + Tonnerre_de_Zeus.getLargeur()) : "Le point de contrôle ne modifie pas les coordonnées X du points droit";
        assert(pdcDeux.gety() == Tonnerre_de_Zeus.getPosY() + hauteur) : "Le point de contrôle ne modifie pas les coordonnées Y du points droit";
    }
}