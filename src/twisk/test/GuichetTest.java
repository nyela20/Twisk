package twisk.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuichetTest extends EtapeTest{

    @org.junit.jupiter.api.Test
    void estUnGuichet() {
        assertTrue(guichetBasketBall.estUnGuichet());
        assertTrue(guichetFootbal.estUnGuichet());
    }

    @org.junit.jupiter.api.Test
    void estUneActivite() {
        assertFalse(guichetBasketBall.estUneActivite());
        assertFalse(guichetFootbal.estUneActivite());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
    }
}