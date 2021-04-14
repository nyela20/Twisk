package twisk;

import twisk.Exceptions.ExceptionObjetNonTrouve;
import twisk.monde.*;
import twisk.outils.ClassLoaderPerso;
import twisk.simulation.Simulation;

public class ClientTwisk {
    public static void main(String[] args) throws ExceptionObjetNonTrouve, ClassNotFoundException {


        /*-monde1-*/
        Monde monde = new Monde();
        ClassLoaderPerso classLoaderPerso = new ClassLoaderPerso(monde.getClass().getClassLoader());
        classLoaderPerso.loadClass("twisk.simulation.Simulation");


        Etape guichet_lion = new Guichet("Guichet_lion", 4);
        Etape guichet_girafe = new Guichet("Guichet_girafe", 4);
        Etape plaine_girafe = new ActiviteRestreinte("plaine_girafe", 2, 1);
        Etape guichet_zebre = new Guichet("Guichet_zebre", 4);
        Etape fast_food = new Activite("fast_food", 2, 1);
        Etape plaine_zebre = new ActiviteRestreinte("plaine_zebre", 2, 1);
        Etape magasin_souvenir = new Activite("magasin_souvenir", 2, 1);
        Etape cage_lion = new ActiviteRestreinte("Cage_lion", 5, 2);


        monde.ajouter(fast_food, guichet_lion, plaine_girafe,cage_lion, guichet_girafe, guichet_zebre, plaine_zebre, magasin_souvenir);

        monde.aCommeEntree(fast_food);
        fast_food.ajouterSuccesseur(guichet_lion);
        guichet_lion.ajouterSuccesseur(cage_lion);
        cage_lion.ajouterSuccesseur(guichet_girafe);
        guichet_girafe.ajouterSuccesseur(plaine_girafe);
        plaine_girafe.ajouterSuccesseur(guichet_zebre);
        guichet_zebre.ajouterSuccesseur(plaine_zebre);
        plaine_zebre.ajouterSuccesseur(magasin_souvenir);
        monde.aCommeSortie(magasin_souvenir);

        Simulation simulation = new Simulation();
        simulation.setNbClients(5);
        simulation.simuler(monde);



        /*-monde2--*/



        Monde monde2 = new Monde();

        ClassLoaderPerso classLoaderPerso2 = new ClassLoaderPerso(monde2.getClass().getClassLoader());
        classLoaderPerso2.loadClass("twisk.simulation.Simulation");

        Activite zoo = new Activite("zoo", 2, 1);
        Etape Guichettob = new Guichet("guichet_tob", 4);
        Activite tob = new ActiviteRestreinte("toboggan", 2, 1);
        Etape GuichetPiscine = new Guichet("guichet_piscine", 3);
        Activite piscine = new ActiviteRestreinte("piscine", 5,2 );


        zoo.ajouterSuccesseur(Guichettob);
        Guichettob.ajouterSuccesseur(tob);
        tob.ajouterSuccesseur(GuichetPiscine);
        GuichetPiscine.ajouterSuccesseur(piscine);
        monde2.ajouter(tob, zoo, Guichettob, GuichetPiscine, piscine);
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

