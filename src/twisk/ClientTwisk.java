package twisk;

import twisk.monde.*;
import twisk.simulation.Simulation;

public class ClientTwisk {
    public static void main(String[] args) {

        Monde monde = new Monde();

        Activite zoo = new Activite("balade_au_zoo", 3, 1);
        Guichet guichettob = new Guichet("acces_au_toboggan", 2);
        Activite tob = new ActiviteRestreinte("toboggan", 2, 1);
        Guichet guichetpiscne = new Guichet("acces_a_la_piscine",2);
        Activite piscine = new ActiviteRestreinte("piscine",3,1);

        monde.ajouter(zoo, guichettob,tob,guichetpiscne,piscine);

        zoo.ajouterSuccesseur(guichettob);
        guichettob.ajouterSuccesseur(tob);
        tob.ajouterSuccesseur(guichetpiscne);
        guichetpiscne.ajouterSuccesseur(piscine);

        monde.aCommeEntree(zoo);
        monde.aCommeSortie(piscine);

        Simulation s = new Simulation();
        s.setNbClients(5);
        s.simuler(monde);
    }



}
