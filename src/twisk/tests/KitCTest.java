package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.outils.KitC;

class KitCTest {

    KitC kit;

    @BeforeEach
    void setUp() {
        kit = new KitC();
    }

    @Test
    void creerFichier() {
        kit.creerFichier("entrer(SasEntree);\n" +
                "delai(0,0);\n" +
                "transfert(SasEntree,Marathon);\n" +
                "delai(15,10);\n" +
                "transfert(Marathon,guichet de Football);\n" +
                "P(ids,1);\n" +
                "transfert(guichet de Football,Football);\n" +
                "delai(90,15);\n" +
                "V(ids,1)\n" +
                "transfert(Football,Tennis) \n" +
                "delai(80,5);\n" +
                "transfert(Tennis,SasSortie);");
    }
}