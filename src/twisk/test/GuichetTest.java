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
}