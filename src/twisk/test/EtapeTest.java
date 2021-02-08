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
    Etape Escrime;
    Etape course;
    Etape Piscine;

    Etape guichetFootball;
    Etape guichetBasketBall;
    Etape guichetEscrime;
    Etape guichetTennis;
    Etape guichetCourse;
    Etape guichetPiscine;

    Etape vipLodge;


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        football = new Activite("Football", 90, 15);
        basketBall = new Activite("BasketBall", 120, 10);
        tennis = new Activite("Tennis", 80, 5);
        marathon = new Activite("Marathon");
        Escrime = new Activite("Escrime",60,12);
        course = new Activite("course",60,12);
        Piscine = new Activite("piscine",120,20);

        guichetFootball = new Guichet("guichet de Football");
        guichetBasketBall = new Guichet("guichet de BasketBall");
        guichetEscrime = new Guichet("guichet escrime");
        guichetTennis = new Guichet("guichet tennis");
        guichetCourse = new Guichet("guichet de course");
        guichetPiscine = new Guichet("guichet piscine");
        vipLodge = new ActiviteRestreinte("vipLodge",360,60);
    }

    @org.junit.jupiter.api.Test
    void ajouterSuccesseur() {

        tennis.ajouterSuccesseur();
        assert(tennis.nbSuccesseurs() == 0) : "Erreur aucun ajout en paramètre";

        tennis.ajouterSuccesseur(tennis);
        assert(tennis.nbSuccesseurs() == 1) : "Impossible d'ajouter une activité après une activité";

        guichetFootball.ajouterSuccesseur(guichetFootball);
        assert (guichetFootball.nbSuccesseurs() == 0) : "Erreur deux guichets se suivents";

        tennis.ajouterSuccesseur(guichetFootball);
        assert(tennis.nbSuccesseurs() == 2) : "Impossible d'ajouter un guichet après une activité qui a déjà un successeur";

        guichetFootball.ajouterSuccesseur(football);
        assert(guichetFootball.nbSuccesseurs() == 1) : "Impossible d'ajouter une activité après un guichet";

        marathon.ajouterSuccesseur(guichetEscrime,guichetCourse);
        assert(marathon.nbSuccesseurs() == 2) : "Erreur deux guichet se suivent";

        guichetEscrime.ajouterSuccesseur(Escrime,Escrime);
        assert(guichetEscrime.nbSuccesseurs() == 1) : "Une meme instance d'activité ne peut pas être doublé";

        guichetPiscine.ajouterSuccesseur(Piscine);
        Piscine.ajouterSuccesseur(guichetTennis,guichetBasketBall);
        assert(guichetTennis.nbSuccesseurs() == 0) : "Erreur le premier paramètre n'est pas succéder par le second";
        assert(Piscine.nbSuccesseurs() == 2) : "Erreur lors de l'ajout avec deux Etapes en paramètre";
        assert(guichetPiscine.nbSuccesseurs() == 1) : "vErreur lors de l'ajout de trois Etapes";
        //jsp si c la fct nbSuccesseurs qui bug ou c quoi

        guichetBasketBall.ajouterSuccesseur(basketBall);
        basketBall.ajouterSuccesseur(guichetBasketBall,basketBall);
        guichetBasketBall.ajouterSuccesseur(basketBall);
        basketBall.ajouterSuccesseur(guichetBasketBall);
        assert(guichetBasketBall.nbSuccesseurs() == 1) : "Erreur lors d'un ajout multiple.";

    }


    @org.junit.jupiter.api.Test
    void iterator() {
        football.ajouterSuccesseur(marathon, tennis, basketBall);
        int compteurTest = 0;
        for (Etape etape : football) {
            compteurTest++;
        }
        assert (compteurTest == 3):"Erreur de nombre dans l'iterateur de successeur";

        Escrime.ajouterSuccesseur(tennis,guichetBasketBall,basketBall,guichetCourse,course,guichetTennis,tennis);
        int compteurTest2 = 0;
        for (Etape etape : Escrime) {
            compteurTest2++;
        }
        assert (compteurTest2 == 7):"Erreur de nombre dans l'iterateur de successeur";

        guichetBasketBall.ajouterSuccesseur();
        assert(!guichetBasketBall.iterator().hasNext()) : "Erreur l'Etape est sans successeur ";

    }
}