package twisk.test;

import static org.junit.jupiter.api.Assertions.*;

class GuichetTest extends EtapeTest {


    @org.junit.jupiter.api.Test
    void estUnGuichet() {
        assertTrue(guichetBasketBall.estUnGuichet(), "Erreur estUnGuichet() ne reconnaît pas un guichet");
        assertFalse(tennis.estUnGuichet() , "Erreur estGuichet() considère une activité comme un guichet");
        assertFalse(yoga.estUnGuichet(), "Erreur estUnGuichet() considère une ActivitéRestreinte comme un guichet");
    }

    @org.junit.jupiter.api.Test
    void estUneActivite() {
        assertTrue(cent_metre.estUneActivite(),"Erreur estUneActivite() ne reconnait pas une activité");
        assertTrue(karate.estUneActivite(),"Erreur estUneActivite() ne considère pas une activitéRestreinte comme une activité");
        assertFalse(guichetBasketBall.estUneActivite(), "Erreur estUneActivite() considère un guichet comme une activité");
    }

    @org.junit.jupiter.api.Test
    void toC() {
        guichetFootball.ajouterSuccesseur(footballRestreint);
        footballRestreint.ajouterSuccesseur(basketBall);
        String res = "P(ids,num_semguichet de Football);\n" +
                "transfert(" + guichetFootball.getNom() + "," + guichetFootball.iterator().next().getNom() + ");\n" +
                "delai(" + footballRestreint.getTemps() + "," + footballRestreint.getEcartTemps() + ");\n" +
                "V(ids,num_semguichet de Football);\n" +
                "transfert(" + guichetFootball.iterator().next().getNom() + "," + guichetFootball.iterator().next().iterator().next().getNom() + ");\n";
        assertEquals(guichetFootball.toC(), res, "La fonction toC ne retourne pas la syntaxe attendue");

        guichetPiscine.ajouterSuccesseur(yoga);
        yoga.ajouterSuccesseur(Piscine);
        String res2 = "P(ids,num_semguichet piscine);\n" +
                "transfert("+guichetPiscine.getNom() +"," + guichetPiscine.iterator().next().getNom() + ");\n" +
                "delai("+ yoga.getTemps() + ","+ yoga.getEcartTemps() +");\n" +
                "V(ids,num_semguichet piscine);\n" +
                "transfert("+guichetPiscine.iterator().next().getNom()+","+ guichetPiscine.iterator().next().iterator().next().getNom()+");\n";
        assertEquals(guichetPiscine.toC(), res2, "La fonction toC ne retourne pas la syntaxe attendue");

    }
}