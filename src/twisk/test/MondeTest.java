package twisk.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.*;
import twisk.outils.FabriqueNumero;
import twisk.simulation.Simulation;

import java.sql.SQLOutput;


class MondeTest {

    Monde monde;

    Etape tennis = new Activite("Tennis", 80, 5);
    Etape marathon = new Activite("Marathon");
    Etape football;
    Etape basketBall;

    Etape guichetFootball;
    Etape guichetBasketBall;

    @BeforeEach
    void setUp() {
        monde = new Monde();

        football = new Activite("Football", 90, 15);
        basketBall = new Activite("BasketBall", 120, 10);

        guichetFootball = new Guichet("guichet de Football");
        guichetBasketBall = new Guichet("guichet de BasketBall");

    }

    @Test
    void ajouter() {
        monde.ajouter(football, basketBall);
        monde.ajouter(guichetFootball, guichetBasketBall);
        int nbEtape = 0;
        for (Etape ignored : monde) {
            nbEtape++;
        }
        assert (nbEtape == 4) : "Nombre d'Etape retourne une valeur incorrecte";

        nbEtape = 0;
        for (int i = 0; i < 10; i++) {
            monde.ajouter(new Activite("activite"));
        }
        for (Etape ignored : monde) {
            nbEtape++;
        }
        assert (nbEtape == 14) : "Nombre d'Etape retourne une valeur incorrecte";

    }

    @Test
    void nbGuichet() {
        monde.ajouter(basketBall);
        assert (monde.nbGuichet() == 0) : "Erreur le nombre de guichets retourné est incorrecte";
        monde.ajouter(guichetBasketBall, football);
        assert (monde.nbGuichet() == 1) : "Erreur le nombre de guichet retourné est incorrecte";
        monde.ajouter(guichetFootball);
        assert (monde.nbGuichet() == 2) : "Erreur le nombre de guichet retourné est incorrecte";
    }

    @Test
    void nbEtapes() {
        FabriqueNumero.getInstance().reset();
        assert (monde.nbEtapes() == 2) : "Erreur le nombre d'étapes retourné est incoorecte";
        monde.ajouter(football);
        assert (monde.nbEtapes() == 3) : "Erreur le nombre d'etapes retourné est incorrecte";
        monde.ajouter(guichetFootball);
        assert (monde.nbEtapes() == 4) : "Erreur le nombre d'etapes retourné est incorrecte";
        monde.ajouter(guichetBasketBall, basketBall);
        assert (monde.nbEtapes() == 6) : "Erreur le nombre d'etapes retourné est incorrecte";
    }

    @Test
    void aCommeEntree() {
        monde.aCommeEntree(football);
    }

    @Test
    void aCommeSortie() {
        monde.aCommeSortie(basketBall);
    }

    @Test
    void toC(){
        Monde monde = new Monde();

        Activite zoo = new Activite("balade_au_zoo", 3, 1);
        Guichet guichet = new Guichet("acces_au_toboggan", 2);
        Activite tob = new ActiviteRestreinte("toboggan", 2, 1);

        zoo.ajouterSuccesseur(guichet);
        guichet.ajouterSuccesseur(tob);

        monde.ajouter(tob, zoo, guichet);

        monde.aCommeEntree(zoo);
        monde.aCommeSortie(tob);

        Simulation s = new Simulation();
        s.setNbClients(5);
        s.simuler(monde);
    }

    @Test
    void toC2(){
        Monde monde = new Monde();

        Activite tob = new ActiviteRestreinte("toboggan", 2, 1);
        Activite zoo = new Activite("balade_au_zoo", 3, 1);

        zoo.ajouterSuccesseur(tob);
        monde.ajouter(tob,zoo);
        monde.aCommeEntree(zoo);
        monde.aCommeSortie(tob);

        Simulation s = new Simulation();
        s.setNbClients(5);
        s.simuler(monde);
    }
}