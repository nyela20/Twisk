package twisk.test;

import org.junit.jupiter.api.Test;
import twisk.monde.Etape;
import twisk.monde.SasEntree;


class SasEntreeTest extends EtapeTest {

    @Test
    void toC() {
        //---------a convertir en test et a multiplier

        Etape sasentree = new SasEntree();
        sasentree.ajouterSuccesseur(football);
    }

}