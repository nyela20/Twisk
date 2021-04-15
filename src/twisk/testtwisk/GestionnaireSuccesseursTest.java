package twisk.testtwisk;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.*;

import static org.junit.jupiter.api.Assertions.*;

class GestionnaireSuccesseursTest {


    Etape football;
    Etape basketBall;
    Etape guichetFootball;
    Etape guichetBasketBall;
    GestionnaireSuccesseurs gesti;

    @BeforeEach
    void setUp() {

        football = new Activite("Football", 90, 15);
        basketBall = new Activite("BasketBall", 120, 10);
        guichetFootball = new Guichet("guichet de Football");
        guichetBasketBall = new Guichet("guichet de BasketBall");
        gesti = new GestionnaireSuccesseurs();
    }

    @Test
    void ajouter() {
        gesti.ajouter(football, basketBall);
        int Compteur = 0;
        for (Etape ignored : gesti) {
            Compteur++;
        }
        assertEquals(Compteur, 2, "Erreur la fonction ajouter ne rajoute par les Etape données en paramètres");
        gesti.ajouter(guichetBasketBall, guichetFootball);
        Compteur = 0;
        for (Etape ignored : gesti) {
            Compteur++;
        }
        assertEquals(Compteur, 4, "Erreur la fonction ajouter ne rajoute par les Etape données en paramètres");
    }

    @Test
    void nbSEtapes() {
        gesti.ajouter();
        assertEquals(gesti.nbSEtapes(), 0, "Erreur nbSEtapes ne retourne pas une valeur valide");
        gesti.ajouter(basketBall);
        assertEquals(gesti.nbSEtapes(), 1, "Erreur nbSEtapes ne retourne pas une valeur valide");
        gesti.ajouter(football);
        assertEquals(gesti.nbSEtapes(), 2, "Erreur nbSEtapes ne retourne pas une valeur valide");
        gesti.ajouter(guichetFootball, guichetBasketBall);
        assertEquals(gesti.nbSEtapes(), 4, "Erreur nbSEtapes ne retourne pas une valeur valide");
    }

    @Test
    void iterator() {
        gesti.ajouter(basketBall);
        assertTrue(gesti.iterator().hasNext(), "Erreur fonctionnement de l'itérateur");
    }

}