package twisk.simulation;

import twisk.monde.Monde;
import twisk.outils.KitC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Simulation {

    public Simulation() {
    }

    public void simuler(Monde monde) {
       // System.out.println(monde);
        KitC kitC = new KitC();
        kitC.creerFichier(monde.toC());
       // kitC.compiler();
    }

}
