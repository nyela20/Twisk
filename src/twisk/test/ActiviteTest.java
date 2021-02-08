package twisk.test;


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
    void TesttoString() {
        assertEquals(football.toString(), "Football");
        assertEquals(marathon.toString(), "Marathon");
    }


}