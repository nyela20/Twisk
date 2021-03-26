package twisk.test;

import static org.junit.jupiter.api.Assertions.*;

class GuichetTest extends EtapeTest {


    @org.junit.jupiter.api.Test
    void estUnGuichet() {
        assertTrue(guichetBasketBall.estUnGuichet(), "Erreur estUnGuichet() ne reconnaît pas un guichet");
    }

    @org.junit.jupiter.api.Test
    void estUneActivite() {
        assertFalse(guichetBasketBall.estUneActivite(), "Erreur estUneActivite() ne reconnaît pas une activité");
        assertFalse(guichetFootball.estUneActivite(), "Erreur estUneActivite() ne reconnaît pas une activité");
    }

    @org.junit.jupiter.api.Test
    void toC() {
        guichetFootball.ajouterSuccesseur(footballRestreint);
        footballRestreint.ajouterSuccesseur(basketBall);
        System.out.println(guichetFootball.toC());
        String res = "P(ids,num_semguichet de Football);\n" +
                "transfert(" + guichetFootball.getNom() + "," + guichetFootball.iterator().next().getNom() + ");\n" +
                "delai(" + footballRestreint.getTemps() + "," + footballRestreint.getEcartTemps() + ");\n" +
                "V(ids,num_semguichet de Football);\n" +
                "transfert(" + guichetFootball.iterator().next().getNom() + "," + guichetFootball.iterator().next().iterator().next().getNom() + ");\n";
        assertEquals(guichetFootball.toC(), res, "La fonction toC ne retourne pas la syntaxe attendue");
    }
}