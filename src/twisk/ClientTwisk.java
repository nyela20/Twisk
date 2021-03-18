package twisk;

import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.Guichet;
import twisk.monde.Monde;
import twisk.simulation.Simulation;

public class ClientTwisk {
    public static void main(String[] args) {

        Monde monde = new Monde();

        /* On crée un monde dans l'ordre */


        Etape fast_food = new Activite("fast_food",5,2);
        Etape guichet_lion = new Guichet("Guichet_lion",5);
        Etape cage_lion = new Activite("Cage_lion",5,2);
        Etape guichet_girafe = new Guichet("Guichet_girafe",5);
        Etape plaine_girafe = new Activite("plaine_girafe",5,2);
        Etape guichet_zebre = new Guichet("Guichet_zebre",5);
        Etape plaine_zebre = new Activite("plaine_zebre",5,2);
        Etape magasin_souvenir = new Activite("magasin_souvenir",5,2);


        monde.ajouter(fast_food,guichet_lion,cage_lion,guichet_girafe,plaine_girafe,guichet_zebre,plaine_zebre,magasin_souvenir);

        monde.aCommeEntree(fast_food);
        monde.aCommeSortie(magasin_souvenir);

        fast_food.ajouterSuccesseur(guichet_lion);
        guichet_lion.ajouterSuccesseur(cage_lion);
        cage_lion.ajouterSuccesseur(guichet_girafe);
        guichet_girafe.ajouterSuccesseur(plaine_girafe);
        plaine_girafe.ajouterSuccesseur(guichet_zebre);
        guichet_zebre.ajouterSuccesseur(plaine_zebre);
        plaine_zebre.ajouterSuccesseur(magasin_souvenir);

        Simulation simulation = new Simulation();
        simulation.simuler(monde);
    }
}
