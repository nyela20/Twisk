package twisk.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.Guichet;
import twisk.monde.Monde;


class MondeTest {

    Monde monde;

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
        guichetBasketBall = new Guichet("BasketBall");

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
        monde.ajouter();
        assert (monde.nbEtapes() == 0) : "Erreur le nombre d'étapes retourné est incoorecte";
        monde.ajouter(football);
        assert (monde.nbEtapes() == 1) : "Erreur le nombre d'etapes retourné est incorrecte";
        monde.ajouter(guichetFootball);
        assert (monde.nbEtapes() == 2) : "Erreur le nombre d'etapes retourné est incorrecte";
        monde.ajouter(guichetBasketBall, basketBall);
        assert (monde.nbEtapes() == 4) : "Erreur le nombre d'etapes retourné est incorrecte";
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
    void ToString(){
        monde.ajouter(football,basketBall,guichetBasketBall,guichetFootball);
        monde.aCommeEntree(football);
        football.ajouterSuccesseur(guichetBasketBall);
        guichetBasketBall.ajouterSuccesseur(basketBall);
        monde.aCommeSortie(basketBall);
        System.out.println(monde.toString());
    }


}