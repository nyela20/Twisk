package twisk.teststwiskig;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.mondeIG.*;
import twisk.outils.FabriqueIdentifiant;


public class ArcIGTest{

    ArcIG arcUn;
    ArcIG arcDeux;
    ArcIG arcTrois;
    ArcIG arcQuatre;

    MondeIG monde;
    PointDeControleIG pdcUn;
    PointDeControleIG pdcDeux;
    PointDeControleIG pdcTrois;
    PointDeControleIG pdcQuatre;

    EtapeIG Tonnerre_de_Zeus;
    ActiviteIG Goudurix;
    EtapeIG PegaseExpress;
    ActiviteIG Ozlris;


    @BeforeEach
    void setUp(){
        monde = new MondeIG();
        String idf = FabriqueIdentifiant.getInstance().getIdentifiant();
        Tonnerre_de_Zeus = new ActiviteIG("Tonnerre_de_Zeus",idf,100,75);
        idf = FabriqueIdentifiant.getInstance().getIdentifiant();
        Goudurix = new ActiviteIG("Goudurix",idf,100,75);
        idf = FabriqueIdentifiant.getInstance().getIdentifiant();
        PegaseExpress = new ActiviteIG("PegaseExpress",idf,100,75);
        idf = FabriqueIdentifiant.getInstance().getIdentifiant();
        Ozlris = new ActiviteIG("Ozlris",idf,100,75);


        pdcUn = new PointDeControleIG(Tonnerre_de_Zeus, "haut");
        pdcDeux = new PointDeControleIG(Goudurix, "bas");
        pdcTrois = new PointDeControleIG(PegaseExpress, "haut");
        pdcQuatre = new PointDeControleIG(Ozlris, "bas");

        arcUn = new ArcIG(pdcUn,pdcDeux);
        arcDeux = new ArcIG(pdcTrois,pdcDeux);
        arcTrois = new ArcIG(pdcQuatre,pdcTrois);
        arcQuatre = new ArcIG(pdcDeux,pdcQuatre);

    }

    @Test
    void aCommeDebut(){
        assert(arcUn.aCommeDebut(Tonnerre_de_Zeus)) : "La fonction aCommeDebut ne retourne pas la valeur attendue";
        assert(arcDeux.aCommeDebut(PegaseExpress)) : "La fonction aCommeDebut ne retourne pas la valeur attendue";
        assert(arcTrois.aCommeDebut(Ozlris)) : "La fonction aCommeDebut ne retourne pas la valeur attendue";
        assert(arcQuatre.aCommeDebut(Goudurix)) : "La fonction aCommeDebut ne retourne pas la valeur attendue";
    }

    @Test
    void aCommeArrive(){
        assert(arcUn.aCommeArrive(Goudurix)) : "La fonction aCommeArrive ne retourne pas la valeur attendue";
        assert(arcDeux.aCommeArrive(Goudurix)) : "La fonction aCommeArrive ne retourne pas la valeur attendue";
        assert(arcTrois.aCommeArrive(PegaseExpress)) : "La fonction aCommeArrive ne retourne pas la valeur attendue";
        assert(arcQuatre.aCommeArrive(Ozlris)) : "La fonction aCommeArrive ne retourne pas la valeur attendue";
    }

    @Test
    void estRelie(){
        assert(arcQuatre.estRelie(Goudurix)) : "La fonction estRelié ne retourne pas une valeur attendue";
        assert(arcQuatre.estRelie(Ozlris)) : "La fonction estRelié ne retourne pas une valeur attendue";
    }


}