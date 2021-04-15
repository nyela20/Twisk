package twisk.testtwisk;

import org.junit.jupiter.api.Test;
import twisk.outils.FabriqueNumero;

import static org.junit.jupiter.api.Assertions.*;

class FabriqueNumeroTest {

    FabriqueNumero fabric = FabriqueNumero.getInstance();

    @Test
    void reset() {
        fabric.reset();
        assertEquals(fabric.getNumeroEtape(), 0);
        assertEquals(fabric.getNumeroSemaphore(), 1);
        fabric.reset();
    }

    @Test
    void getNumeroEtape() {
        fabric.reset();
        assertEquals(fabric.getNumeroEtape(), 0);
        assertEquals(fabric.getNumeroEtape(), 1);
        assertEquals(fabric.getNumeroEtape(), 2);
        assertEquals(fabric.getNumeroEtape(), 3);
        fabric.reset();
    }

    @Test
    void getNumeroSemaphore() {
        fabric.reset();
        assertEquals(fabric.getNumeroSemaphore(), 1);
        assertEquals(fabric.getNumeroSemaphore(), 2);
        assertEquals(fabric.getNumeroSemaphore(), 3);
        assertEquals(fabric.getNumeroSemaphore(), 4);
        fabric.reset();
    }
}