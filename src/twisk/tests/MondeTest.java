package twisk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.*;
import twisk.outils.FabriqueNumero;
import twisk.simulation.Simulation;

import java.io.IOException;


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

    @Test
    void toC3(){
        Monde monde3 = new Monde();

        Activite zoo = new Activite("zoo", 2, 1);
        Activite zoocool = new Activite("zoocool",2,1);
        Activite tob = new Activite("toboggan", 2, 1);
        Etape GuichetPiscine = new Guichet("guichet_piscine", 3);
        Activite piscine = new ActiviteRestreinte("piscine", 2, 1);

        zoo.ajouterSuccesseur(zoocool);
        tob.ajouterSuccesseur(GuichetPiscine);
        GuichetPiscine.ajouterSuccesseur(piscine);
        monde3.ajouter(tob);
        monde3.aCommeEntree(tob);
        monde3.ajouter(zoo);
        monde3.aCommeEntree(zoo);
        monde3.ajouter(zoocool);
        monde3.aCommeSortie(zoocool);
        monde3.ajouter(GuichetPiscine);
        monde3.ajouter(piscine);
        monde3.aCommeSortie(piscine);
        monde3.aCommeEntree(zoo,tob);


        Simulation s = new Simulation();
        s.setNbClients(15);
        try {
            s.simuler(monde3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void toC4() {
        Monde monde = new Monde();
        Etape lolo = new Activite("lolo", 5, 2);
        monde.ajouter(lolo);
        Etape popo = new ActiviteRestreinte("popo", 5, 2);
        monde.ajouter(popo);
        Etape guichet = new Guichet("guichet", 4);
        monde.ajouter(guichet);
        monde.aCommeSortie(popo);
        lolo.ajouterSuccesseur(guichet);
        monde.aCommeEntree(lolo);
        guichet.ajouterSuccesseur(popo);
        Simulation simulation = new Simulation();
        simulation.setNbClients(3);
        try {
            simulation.simuler(monde);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
