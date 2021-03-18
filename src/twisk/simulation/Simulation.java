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


        //--------------Ecriture du code MAIN.C

        int nbClients = 10;
        int nbEtapes = monde.nbEtapes();
        int[] TableauDeJetons = new int[monde.nbGuichet()];
        TableauDeJetons[0] = 2;
        TableauDeJetons[1] = 2;
        TableauDeJetons[2] = 2;

        int[] tableauP = start_simulation(nbEtapes, 3, nbClients, TableauDeJetons);


        //----------  afficher Client 1------------

        System.out.print("les clients : ");

        for (int i = 0; i < nbClients - 1; i++) {
            System.out.print(tableauP[i]+ " ");
        }
        System.out.print(tableauP[nbClients - 1] + "\n\n");


        //----------  afficherEmplacementClient 2------------


        //La condition de fin de boucle ci-dessous n'arrête pas l'itération, à changer !
        while (ou_sont_les_clients(nbEtapes, nbClients)[((nbClients + 1) * nbEtapes) - (nbClients + 1)] != nbClients) {

            int[] tabEmplaceClients;
            tabEmplaceClients = ou_sont_les_clients(nbEtapes, nbClients);

            int debut = 1, fin = debut + tabEmplaceClients[0], delta;

            for (int i = 0; i < nbEtapes; i++) {
                System.out.print("etape " + i + " (etape) " + tabEmplaceClients[debut - 1] + "client(s)");

                for (int j = debut; j < fin; j++) {
                    System.out.print(" " + tabEmplaceClients[j] + " ");
                }
                System.out.print("\n");
                try {
                    delta = tabEmplaceClients[debut + nbClients];
                }catch(Exception e){
                    delta = 0;
                }
                System.out.println("delta : " + delta);
                debut = debut + nbClients + 1;
                fin = debut + delta;
            }

            System.out.println();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        nettoyage();
    }
}