package twisk.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ActiviteTest extends EtapeTest {

    @org.junit.jupiter.api.Test
    void estUnGuichet() {
        assertFalse(basketBall.estUnGuichet());
        assertFalse(football.estUnGuichet());
    }

    @org.junit.jupiter.api.Test
    void estUneActivite() {
        assertTrue(football.estUneActivite());
        assertTrue(basketBall.estUneActivite());
    }


}