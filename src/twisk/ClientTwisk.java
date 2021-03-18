package twisk;

import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.Guichet;
import twisk.monde.Monde;
import twisk.simulation.Simulation;

public class ClientTwisk {
    public static void main(String[] args) {

        Monde monde = new Monde();

        Etape football = new Activite("Football", 5, 2);
        Etape tennis = new Activite("Tennis", 5, 2);
        Etape marathon = new Activite("Marathon");

        Etape guichetFootbal = new Guichet("guichet_de_Football",2);
        monde.ajouter(marathon,guichetFootbal,football,tennis);

        monde.aCommeEntree(marathon);
        monde.aCommeSortie(tennis);

        marathon.ajouterSuccesseur(guichetFootbal);
        guichetFootbal.ajouterSuccesseur(football);
        football.ajouterSuccesseur(tennis);

        Simulation simulation = new Simulation();
        simulation.simuler(monde);


    }
}
