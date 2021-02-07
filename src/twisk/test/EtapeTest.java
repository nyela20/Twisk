package twisk.test;

import twisk.monde.Activite;
import twisk.monde.ActiviteRestreinte;
import twisk.monde.Etape;
import twisk.monde.Guichet;


class EtapeTest {

    Etape football;
    Etape basketBall;
    Etape tennis;
    Etape marathon;

    Etape guichetFootbal;
    Etape guichetTennis;
    Etape guichetBasketBall;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        football = new Activite("Football", 90, 15);
        basketBall = new Activite("BasketBall", 120, 10);
        tennis = new Activite("Tennis", 80, 5);
        marathon = new Activite("Marathon");

        guichetFootbal = new Guichet("guichet de Football");
        guichetBasketBall = new Guichet("BasketBall");
        guichetTennis = new Guichet("guichet de Tennis");
    }

    @org.junit.jupiter.api.Test
    void ajouterSuccesseur() {

        //test a multiplier neyla

        //test sur une activité
        football.ajouterSuccesseur(basketBall, football);
        assert (football.nbSuccesseurs() == 2) : "nombre succeseur de football incorrect";
        //test sur un guichet
        guichetBasketBall.ajouterSuccesseur(guichetBasketBall);
        assert (guichetBasketBall.nbSuccesseurs() == 0) : " erreur deux guichet se suivent dans l'arc";

        guichetFootbal.ajouterSuccesseur(football);
        assert (guichetFootbal.nbSuccesseurs() == 1) : "impossible d'ajouter une activité apres un guichet";
    }


    @org.junit.jupiter.api.Test
    void iterator() {

        football.ajouterSuccesseur(marathon, tennis, basketBall);
        int comptSucce = 0;
        for (Etape e : football) {
            comptSucce++;
        }
        assert (comptSucce == 3):"Erreur de nombre dans l'iterateur de successeur";
    }
}