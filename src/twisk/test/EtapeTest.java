package twisk.test;

import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.Guichet;

import static org.junit.jupiter.api.Assertions.*;

class EtapeTest {

    Etape football;
    Etape basketBall;
    Etape tennis;
    Etape marathon;

    Etape guichetFootbal;
    Etape guichetTennis;


    @org.junit.jupiter.api.BeforeEach

    void setUp() {
        football = new Activite("Football", 90, 15);
        basketBall = new Activite("BasketBall",120,10);

        guichetFootbal = new Guichet("guichet de Football");
        guichetTennis = new Guichet("guichet de Tennis");
    }

    @org.junit.jupiter.api.Test
    void ajouterSuccesseur() {
    }

    @org.junit.jupiter.api.Test
    void estUneActivite() {
        assertTrue(football.estUneActivite());
        assertFalse(guichetFootbal.estUneActivite());
    }

    @org.junit.jupiter.api.Test
    void estUnGuichet() {
        assertFalse(football.estUnGuichet());
        assertTrue(guichetFootbal.estUnGuichet());
    }
}