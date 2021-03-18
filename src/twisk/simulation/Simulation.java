package twisk.simulation;

import twisk.monde.Monde;
import twisk.outils.KitC;


public class Simulation {

    public Simulation() {
    }

    public native int[] start_simulation(int NB_ETAPES, int NB_GUICHET, int NB_CLIENTS, int[] tabJeton);

    public native int[] ou_sont_les_clients(int NB_ETAPES, int NB_GUICHET);

    public native void nettoyage();

    public void simuler(Monde monde) {

        System.out.println(monde);

        KitC kitC = new KitC();
        kitC.creerEnvironnement();
        kitC.creerFichier(monde.toC());
        kitC.compiler();
        kitC.construireLaLibrairie();
        System.load("/tmp/twisk/libTwisk.so");

        //--------------Ecriture du cde MAIN.C
        /*  tu dois transcrire ici le contenu de ton code Main.c, tout les fonctions sont deja definis, regardes le profils de la classe !! */
    }
}
