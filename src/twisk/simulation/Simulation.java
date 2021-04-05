package twisk.simulation;

import twisk.monde.Monde;
import twisk.outils.KitC;


public class Simulation {


    public Simulation(){ }

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

        int NB_CLIENTS = 10;
        int NB_GUICHETS = monde.nbGuichet();
        int NB_ETAPES = monde.nbEtapes();


        //instanciation des jetons
        int[] TableauDeJetons = new int[monde.nbGuichet()];
        for (int i = 0; i < NB_GUICHETS; i++) {
            TableauDeJetons[i] = monde.getnbJetonsNiemeEtape(i);
        }

        int[] tableauP = start_simulation(NB_ETAPES, 3, NB_CLIENTS, TableauDeJetons);


        //----------  Affiche les clients en début de simulation------------

        System.out.print("les clients : ");
        for (int i = 0; i < NB_CLIENTS - 1; i++) {
            System.out.print(tableauP[i] + " ");
        }
        System.out.print(tableauP[NB_CLIENTS - 1] + "\n\n");

        //----------  afficherEmplacementClient 2------------

        boolean findeBoucle = false;
        while (!findeBoucle) {

            //condition d'arret de la bouche
            if (ou_sont_les_clients(NB_ETAPES, NB_CLIENTS)[monde.getSasSortieNumeroEtape() * NB_CLIENTS + 1] == NB_CLIENTS) {
                findeBoucle = true;
            }

            int[] tabEmplaceClients = ou_sont_les_clients(NB_ETAPES, NB_CLIENTS);

            int debut = 1, fin = debut + tabEmplaceClients[0], delta, k = 0;
            String sas = "SasEntree";

            for (int i = 0; i < 2; i++) {
                System.out.print("etape " + i + " " + sas + " " + tabEmplaceClients[NB_CLIENTS * i + k] + " client(s) ");
                for (int j = debut; j < fin; j++) {
                    System.out.print(" " + tabEmplaceClients[j] + " ");
                }
                System.out.println();
                delta = tabEmplaceClients[debut + NB_CLIENTS];
                debut = debut + NB_CLIENTS + 1;
                fin = debut + delta;
                k++;
                sas = "SasSortie";
            }

            for (int i = 2; i < NB_ETAPES; i++) {
                System.out.print("etape " + i + " " + monde.getNomNiemeEtape(i - 2) + " " + tabEmplaceClients[debut - 1] + " client(s) ");
                for (int j = debut; j < fin; j++) {
                    System.out.print(" " + tabEmplaceClients[j] + " ");
                }
                System.out.print("\n");

                try {
                    delta = tabEmplaceClients[debut + NB_CLIENTS];
                }
                catch (Exception e){
                    delta=0;
                }
                debut = debut + NB_CLIENTS + 1;
                fin = debut + delta;
            }
            System.out.println();

            //mise en pause
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        nettoyage();
    }
}