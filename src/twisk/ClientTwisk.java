package twisk;

import twisk.monde.*;
import twisk.simulation.Simulation;

public class ClientTwisk {
    public static void main(String[] args) {

        Monde monde = new Monde();

        Activite zoo = new Activite("balade_au_zoo", 3, 1);
        Guichet guichet = new Guichet("acces_au_toboggan", 2);
        Activite tob = new ActiviteRestreinte("toboggan", 2, 1);

        zoo.ajouterSuccesseur(guichet);
        guichet.ajouterSuccesseur(tob);

        monde.ajouter(guichet, tob, zoo);

        monde.aCommeEntree(zoo);
        monde.aCommeSortie(tob);

        Simulation s = new Simulation();
        s.setNbClients(5);
        s.simuler(monde);
    }



}
