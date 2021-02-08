package twisk.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuichetTest extends EtapeTest{


    @org.junit.jupiter.api.Test
    void estUnGuichet() {
        assertTrue(guichetBasketBall.estUnGuichet(), "Erreur estUnGuichet() ne reconnaît pas un guichet");
    }

    @org.junit.jupiter.api.Test
    void estUneActivite(){
        assertFalse(guichetBasketBall.estUneActivite(),"Erreur estUneActivite() ne reconnaît pas une activité");
        assertFalse(guichetFootball.estUneActivite(),"Erreur estUneActivite() ne reconnaît pas une activité");
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        assertTrue(guichetBasketBall.toString().equals("guichet de BasketBall"));
        assertTrue(guichetFootball.toString().equals("guichet de Football"));
    }
}