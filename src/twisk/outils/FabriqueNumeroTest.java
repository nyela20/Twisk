package twisk.outils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FabriqueNumeroTest {

    FabriqueNumero fabric;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        fabric = FabriqueNumero.getInstance();
    }
    @Test
    void getNumeroEtape() {
        fabric = FabriqueNumero.getInstance();
        assertEquals(fabric.getNumeroEtape(),0);
        assertEquals(fabric.getNumeroEtape(),1);
        assertEquals(fabric.getNumeroEtape(),2);
        assertEquals(fabric.getNumeroEtape(),3);
    }

    @Test
    void getNumeroSemaphore() {
        assertEquals(fabric.getNumeroSemaphore(),1);
        assertEquals(fabric.getNumeroSemaphore(),2);
        assertEquals(fabric.getNumeroSemaphore(),3);
        assertEquals(fabric.getNumeroSemaphore(),4);
    }

    @Test
    void reset() {
        fabric.reset();
        assertEquals(fabric.getNumeroEtape(),0);
        assertEquals(fabric.getNumeroSemaphore(),1);
    }

}