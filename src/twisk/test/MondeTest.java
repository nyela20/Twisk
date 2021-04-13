package twisk.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.*;
import twisk.outils.FabriqueNumero;
import twisk.simulation.Simulation;



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
        basketBall = new ActiviteRestreinte("BasketBall");

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

        Activite tob = new Activite("toboggan", 2, 1);
        Activite zoo = new Activite("balade_au_zoo", 3, 1);

        zoo.ajouterSuccesseur(tob);
        monde.ajouter(tob,zoo);
        monde.aCommeEntree(zoo);
        monde.aCommeSortie(tob);

        Simulation s = new Simulation();
        s.setNbClients(5);
        try {
            s.simuler(monde);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void toC2(){
        Monde monde2 = new Monde();

        Activite zoo = new Activite("zoo", 2, 1);
        Etape Guichettob = new Guichet("guichet_tob", 2);
        Activite tob = new ActiviteRestreinte("toboggan", 2, 1);
        Etape GuichetPiscine = new Guichet("guichet_piscine", 3);
        Activite piscine = new ActiviteRestreinte("piscine", 2, 1);


        zoo.ajouterSuccesseur(Guichettob);
        Guichettob.ajouterSuccesseur(tob);
        tob.ajouterSuccesseur(GuichetPiscine);
        GuichetPiscine.ajouterSuccesseur(piscine);
        monde2.ajouter(tob,zoo,Guichettob,GuichetPiscine,piscine);
        monde2.aCommeEntree(zoo);
        monde2.aCommeSortie(piscine);

        Simulation s = new Simulation();
        s.setNbClients(15);
        try {
            s.simuler(monde2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}