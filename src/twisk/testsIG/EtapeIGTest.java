package twisk.testsIG;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.mondeIG.ActiviteIG;
import twisk.mondeIG.EtapeIG;
import twisk.mondeIG.MondeIG;
import twisk.outils.FabriqueIdentifiant;


class EtapeIGTest {

    MondeIG parcAsterix;

    EtapeIG Discobelix;
    EtapeIG PegaseExpress;
    ActiviteIG Goudurix;
    ActiviteIG Ozlris;
    ActiviteIG Tonnerre_de_Zeus;
    ActiviteIG Le_Grand_Splatch;


    @BeforeEach
    void setup(){

        parcAsterix = new MondeIG();
        String idf = FabriqueIdentifiant.getInstance().getIdentifiantActivite();
        Discobelix = new ActiviteIG("Discobelix",idf,100,75);
        idf = FabriqueIdentifiant.getInstance().getIdentifiantActivite();
        PegaseExpress = new ActiviteIG( "PegaseExpress",idf,100,75);
        idf = FabriqueIdentifiant.getInstance().getIdentifiantActivite();
        Goudurix = new ActiviteIG("Goudurix",idf,100,75);
        idf = FabriqueIdentifiant.getInstance().getIdentifiantActivite();
        Ozlris = new ActiviteIG("Ozliris",idf,100,75);
        idf = FabriqueIdentifiant.getInstance().getIdentifiantActivite();
        Tonnerre_de_Zeus = new ActiviteIG("Tonnerre_de_Zeus",idf,100,75);
        idf = FabriqueIdentifiant.getInstance().getIdentifiantActivite();
        Le_Grand_Splatch = new ActiviteIG("Le_Grand_Splatch",idf,100,75);
    }

    @Test
    void ajouterTousLesPDC(){
        //4 pdcs sont ajouter à l'instanciation
        assert(Discobelix.nombreDePointDeControle() == 4) : "La fonction ajouterpdc n'ajoute pas de points de contrôle.";
        Discobelix.ajouterPointDeControle();
        assert(Discobelix.nombreDePointDeControle() == 8) : "La fonction ajouterpdc n'ajoute pas de points de contrôle.";
    }

    @Test
    void renommer(){
        Discobelix.renommer("Discobelix2.0");
        assert(Discobelix.getNom().equals("Discobelix2.0")) : "La fonction renommer ne change pas l'identifiant de l'Etape";
    }

    @Test
    void repositionnerTousLesPDC(){
        Goudurix.repositionnerTousLesPDC();
        assert(Goudurix.nombreDePointDeControle() == 4) : "La taille du tableau de point de controle à changé";
    }

    @Test
    void deplacerEtape(){
        Le_Grand_Splatch.deplacerEtape(565,454);
        assert(Le_Grand_Splatch.getPosX() == 565): "Erreur l' etape n'a pas été déplacé à l'abscisse souhaité";
        assert(Le_Grand_Splatch.getPosY() == 454): "Erreur l' etape n'a pas été déplacé à l'ordonnée souhaité";
    }

}