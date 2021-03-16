package twisk.simulation;

import twisk.monde.Monde;
import twisk.outils.KitC;


public class Simulation {

    public Simulation() {
    }

    public void simuler(Monde monde) {
        System.out.println(monde);
        KitC kitC = new KitC();
        kitC.creerEnvironnement();
        kitC.creerFichier(monde.toC());
        kitC.compiler();
        kitC.construireLaLibrairie();
    }

}
