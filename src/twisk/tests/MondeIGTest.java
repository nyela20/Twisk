package twisk.tests;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.exceptionstwisk.ExceptionVueMenu;
import twisk.exceptionstwisk.ExceptionsInvaliditeSurLesArcs;
import twisk.monde.*;
import twisk.outils.FabriqueIdentifiant;

import java.util.Iterator;

class MondeIGTest {

    MondeIG parcAsterix;

    ActiviteIG Ozlris;
    EtapeIG PegaseExpress;
    ActiviteIG Tonnerre_de_Zeus;
    ActiviteIG Le_Grand_Splatch;

    PointDeControleIG pdcUn;
    PointDeControleIG pdcDeux;
    PointDeControleIG pdcTrois;
    PointDeControleIG pdcQuatre;
    PointDeControleIG pdcCinq;
    PointDeControleIG pdcSix;




    @BeforeEach
    void setUp() {
        parcAsterix = new MondeIG();
        String idf = FabriqueIdentifiant.getInstance().getIdentifiant();
        Ozlris = new ActiviteIG("Ozlris",idf,100,75);
        idf = FabriqueIdentifiant.getInstance().getIdentifiant();
        PegaseExpress = new ActiviteIG("PegaseExpress",idf,100,75);
        idf = FabriqueIdentifiant.getInstance().getIdentifiant();
        Tonnerre_de_Zeus = new ActiviteIG("Tonnerre_de_Zeus",idf,100,75);
        idf = FabriqueIdentifiant.getInstance().getIdentifiant();
        Le_Grand_Splatch = new ActiviteIG("Le_Grand_Splatch",idf,100,75);

        pdcUn = new PointDeControleIG(Le_Grand_Splatch, "droite");
        pdcDeux = new PointDeControleIG(Le_Grand_Splatch, "gauche");
        pdcTrois = new PointDeControleIG(Tonnerre_de_Zeus, "haut");
        pdcQuatre = new PointDeControleIG(Tonnerre_de_Zeus,"bas");
        pdcCinq = new PointDeControleIG(Ozlris,"bas");
        pdcSix = new PointDeControleIG(Ozlris,"bas");
    }



    @Test
    void ajouterEtape() {
        assert(parcAsterix.sizeTableauEtapeIG() == 1) : "A l'initialisation le monde possède déjà une étape";
        parcAsterix.ajouter("Activite");
        assert(parcAsterix.sizeTableauEtapeIG() == 2) : "La fonction ajouter ne rajoute pas d'activite dans le monde";
    }

    @Test
    void deplacerEtape() {
        Ozlris.deplacerEtape(268,498);
        assert(Ozlris.getPosX() == 268) : "La fonction ne déplace pas correctement l'abscisse de l'étape";
        assert(Ozlris.getPosY() == 498) : "La fonction ne déplace pas corectement l'ordonnée de œl'étape";
        assert(Ozlris.getTabPointDeControlesize() == 4) : "La fonction a modifié le nombre de pdc dans le tableaudePdc de l'Etape";
        for (Iterator<ArcIG> it = parcAsterix.iteratorArcIG(); it.hasNext(); ) {
            ArcIG arc = it.next();
            assert(!arc.estRelie(Ozlris)) : "Les arcs réliés à l'Etape supprimer n'ont pas été supprimer";
        }
    }

    @Test
    void estSelectionneEtape(){
        assert(!Ozlris.estSelectionne()) : "La fonction setEstSelectionne ne selectionne pas l'étape";
        parcAsterix.setEstSelectionne(Ozlris,true);
        assert(Ozlris.estSelectionne()) : "La fonction setEstSelectionne ne selectionne pas l'étape";
        parcAsterix.setEstSelectionne(Ozlris,false);
        assert(!Ozlris.estSelectionne()) : "La fonction setEstSelectionne ne dé-selectionne pas l'étape";
    }

    @Test
    void estSelectionneArc(){
        try {
            parcAsterix.ajouter(pdcUn, pdcTrois);
            parcAsterix.ajouter(pdcQuatre, pdcCinq);
            parcAsterix.ajouter(pdcSix, pdcDeux);
        }catch(ExceptionsInvaliditeSurLesArcs e){
            e.printStackTrace();
        }
            Iterator<ArcIG> it = parcAsterix.iteratorArcIG();
            while (it.hasNext()) {
                ArcIG arc = it.next();
                arc.setEstSelectionne(true);
            }
        {
            Iterator<ArcIG> itarc = parcAsterix.iteratorArcIG();
            while (itarc.hasNext()) {
                ArcIG arc = itarc.next();
                assert(arc.estSelectionne()) : "Erreur l'arc n'est pas sélécitonnée";
            }
        }
    }

    @Test
    void pointDeControleSelectionne(){
        parcAsterix.pointDeControleSelectionne(pdcUn);
        assert(parcAsterix.sizeTableauPointIG() == 1) : "Erreur lorsqu'un point estSelectionne il devrait etre rajouter dans le monde.";
        parcAsterix.pointDeControleSelectionne(pdcTrois);
        assert(parcAsterix.sizeTableauPointIG() == 0) : "Erreur lorsque deux points sontSelectionne, on crée l'arc et on vide le tableau.";
        assert(parcAsterix.sizeTableauArcIG() == 1) : "Le fonction n'a pas rajouter l'arc dans le monde";
    }

    @Test
    void ajouterArc(){
        assert(parcAsterix.sizeTableauArcIG() == 0) : "A l'initialisation le monde ne possède aucun Arc";
        try {
            parcAsterix.ajouter(pdcUn,pdcTrois);
        } catch (ExceptionsInvaliditeSurLesArcs e) {
            e.printStackTrace();
        }
        assert (parcAsterix.sizeTableauArcIG() == 1) : "La fonction rajouter ne rajoute pas d'arc dans le monde";
    }

    @Test
    void deplacementEtape(){
        parcAsterix.deplacerUneEtape(Ozlris,187,362);
        assert(Ozlris.getPosX() == 187) : "La fonction ne déplace pas correctement x de 'étape";
        assert(Ozlris.getPosY() == 362) : "La fonction ne déplace pas correctement y de 'étape";
    }

    @Test
    void assigerEntree(){
        parcAsterix.iterator().next().setEstSelectionne(true);
        parcAsterix.assignerEntree();
        assert(parcAsterix.iterator().next().estUneEntree()) : "La fonction n'a pas assigner comme entrée les éléments séléctionné";
        parcAsterix.iterator().next().setEstSelectionne(true);
        parcAsterix.assignerEntree();
        assert(!parcAsterix.iterator().next().estUneEntree()) : "La fonction n'a pas assigné comme non-entréé les éléments séléctionné";
    }

    @Test
    void assigerSortie(){
        parcAsterix.iterator().next().setEstSelectionne(true);
        parcAsterix.assignerSortie();
        assert(parcAsterix.iterator().next().estUneSortie()) : "La fonction n'a pas assigner comme sortie les éléments séléctionné";
        parcAsterix.iterator().next().setEstSelectionne(true);
        parcAsterix.assignerSortie();
        assert(!parcAsterix.iterator().next().estUneSortie()) : "La fonction n'a pas assigner comme non-sortie les éléments séléctionné";
    }

    @Test
    void getEtape(){
        try{
        for(int i=1; i < 5; i++){
            parcAsterix.ajouter("Activite");
            EtapeIG tmp = parcAsterix.getEtape(parcAsterix.iterator().next().getIdentifiant());
            assert(tmp.equals(parcAsterix.iterator().next())) : "La fonction getEtape ne retourne pas l'Etape attendue";
            assert(tmp.getIdentifiant().equals(parcAsterix.iterator().next().getIdentifiant())) : "La fonction getEtape ne retourne pas l'Etape attendue";
        }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void EffacerLesSelections(){
        //test effacer selection Etape
        parcAsterix.iterator().next().setEstSelectionne(true);
        parcAsterix.effacerLaSelection();
        assert(!parcAsterix.iterator().next().estSelectionne()) : "La fonction n'a pas déséléctionné les éléments séléctionné";
        //test effacer selection Arc
        try {
            parcAsterix.ajouter(pdcUn,pdcTrois);
        } catch (ExceptionsInvaliditeSurLesArcs exceptionsInvaliditeSurLesArcs) {
            exceptionsInvaliditeSurLesArcs.printStackTrace();
        }
        parcAsterix.iteratorArcIG().next().setEstSelectionne(true);
        parcAsterix.effacerLaSelection();
        assert (!parcAsterix.iteratorArcIG().next().estSelectionne()) : "La fonction n'a pas déséléctionné les éléments séléctionné";
    }

    @Test
    void SupprimerEtapesEtArcs(){
        parcAsterix.iterator().next().setEstSelectionne(true);
        parcAsterix.supprimerEtapesEtArcsSelectionnes();
        assert(parcAsterix.sizeTableauEtapeIG() == 0) : "La fonction n'a pas supprimer les éléments déselectionnée";
    }

    @Test
    void viderTableauPDC(){
        parcAsterix.pointDeControleSelectionne(pdcUn);
        assert(parcAsterix.sizeTableauPointIG() == 1) : "Les PointsDeControles n'ont pas été ajouter par la fonction pointDeControleSelectionne().";
        parcAsterix.viderTableauPointsDeControle();
        assert(parcAsterix.sizeTableauPointIG() == 0) : "Les PointsDeControles n'ont pas supprimer.";
    }

    @Test
    void IteratorEtapeEtArc(){
        parcAsterix.ajouter("Activite");
        parcAsterix.ajouter("Activite");
        int i=0;
        for(EtapeIG ignored : parcAsterix){
            i++;
        }
        assert(i == 3) : "Erreur itératorEtape";
        try {
            parcAsterix.ajouter(pdcUn,pdcTrois);
            parcAsterix.ajouter(pdcQuatre,pdcCinq);
        } catch (ExceptionsInvaliditeSurLesArcs e) {
            e.printStackTrace();
        }
        int j=0;
        Iterator<ArcIG> itarc = parcAsterix.iteratorArcIG();
        while (itarc.hasNext()) {
            itarc.next();
            j++;
        }
        assert(j == 2) : "Erreur itérateurArc";
    }

    @Test
    void renommeEtape(){
        parcAsterix.ajouter("Activite");
        parcAsterix.ajouter("Activite");
        parcAsterix.ajouter("Activite");
        for(EtapeIG etapeIG : parcAsterix){
            etapeIG.setEstSelectionne(true);
        }
        parcAsterix.renommerEtape("NouveauNom");
        for(EtapeIG etapeIG : parcAsterix){
            assert(etapeIG.getNom().equals("NouveauNom")) : "La fonction n'a pas renommer l'Etape comme demandé";
        }
    }

    @Test
    void nombreAtiviteSelectionne(){
        parcAsterix.ajouter("Activite");
        parcAsterix.ajouter("Activite");
        parcAsterix.ajouter("Activite");
        for(EtapeIG etapeIG : parcAsterix){
            etapeIG.setEstSelectionne(true);
        }
        assert(parcAsterix.nombreActiviteSelectionne() == 4) : "Erreur le nombre cet fonction ne retourne pas une valeur exacte";
    }

    @Test
    void assignerDelaiAEtape(){
        parcAsterix.iterator().next().setEstSelectionne(true);
        try {
            parcAsterix.assignerDelaiAEtape(12);
        } catch (ExceptionVueMenu exceptionVueMenu) {
            exceptionVueMenu.printStackTrace();
        }
        ActiviteIG activiteIG = (ActiviteIG) parcAsterix.iterator().next();
        assert(activiteIG.getDelai() == 12) : "La fonction n'assigne pas de délai à l'Etape";
    }


    @Test
    void assigerEcartTempsAEtape(){
        parcAsterix.iterator().next().setEstSelectionne(true);
        try {
            parcAsterix.assignerEcartTempsAEtape(45);
        } catch (ExceptionVueMenu exceptionVueMenu) {
            exceptionVueMenu.printStackTrace();
        }
        ActiviteIG activiteIG = (ActiviteIG) parcAsterix.iterator().next();
        assert(activiteIG.getEcarttemps() == 45) : "Erreur la fonction n'assigne pas d'écart temps à l'Etape";
    }







}