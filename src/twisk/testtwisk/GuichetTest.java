package twisk.testtwisk;

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
        assertTrue(karate.estUneActiviteRestreinte(),"Erreur estUneActivite() ne considère pas une activitéRestreinte comme une activité");
        assertFalse(guichetBasketBall.estUneActivite(), "Erreur estUneActivite() considère un guichet comme une activité");
    }

    @org.junit.jupiter.api.Test
    void toC() {
        guichetFootball.ajouterSuccesseur(yoga);
        footballRestreint.ajouterSuccesseur(basketBall);

    }
}