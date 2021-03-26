package twisk.test;


import twisk.monde.Activite;

import static org.junit.jupiter.api.Assertions.*;

class ActiviteTest extends EtapeTest {


    @org.junit.jupiter.api.Test
    void estUnGuichet() {
        assertFalse(basketBall.estUnGuichet(), "Erreur estUnGuichet() ne reconnaît pas un guichet");
        assertFalse(football.estUnGuichet(), "Erreur estUnGuichet() ne reconnaît pas un guichet");
    }

    @org.junit.jupiter.api.Test
    void estUneActivite() {
        assertTrue(football.estUneActivite(), "Erreur estUneActivite() ne reconnaît pas une activité");
        assertTrue(basketBall.estUneActivite(), "Erreur estUneActivite() ne reconnaît pas une activité");
    }

    @org.junit.jupiter.api.Test
     void toC(){
        Tennis_de_table.ajouterSuccesseur(guichet_Fast_Food);
        String res = "\ndelai("+Tennis_de_table.getTemps()+","+Tennis_de_table.getEcartTemps()+");\n" +
                "transfert(Tennis_de_table,"+Tennis_de_table.iterator().next().getNom() +");\n";
        assertEquals(Tennis_de_table.toC(),res,"La fonction toC ne retourne pas la syntaxe attendue");

        cent_metre.ajouterSuccesseur(Tennis_de_table);
        res = "\ndelai("+cent_metre.getTemps()+","+cent_metre.getEcartTemps()+");\n" +
                "transfert(cent_metre,"+cent_metre.iterator().next().getNom() +");\n";
        assertEquals(cent_metre.toC(),res,"La fonction ne retroune pas la syntaxe attendue");

    }
}