package twisk.simulation;

import twisk.monde.Monde;
import twisk.outils.KitC;



public class Simulation {


    private int NB_CLIENTS;
    private GestionnaireClients gestionnaireClients;

    public Simulation() {
        gestionnaireClients = new GestionnaireClients();
    }

    public void setNbClients(int nbClients) {
        assert(nbClients > 0) : "erreur valeur nbclients.";
        this.NB_CLIENTS = nbClients;
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

            tabEmplaceClients = ou_sont_les_clients(NB_ETAPES, NB_CLIENTS);
            affichage_tab(tabEmplaceClients, NB_ETAPES, monde);
            System.out.println("\n");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        System.out.println();

        //mise en pause
        nettoyage();
    }

    public void affichage_tab(int[] tab, int nb_etapes, Monde monde) {

        for (int i = 0; i < nb_etapes; i++) {

            System.out.print("Etape " + i + " (" + monde.getNomNiemeEtape(i) + ") " + tab[(i * (NB_CLIENTS + 1))] + " clients :\t");

            for (int j = 1; j <= tab[(i * (NB_CLIENTS + 1))]; j++) {
                if (tab[(i * (NB_CLIENTS + 1) + j)] != 0) {
                    System.out.print(tab[(i * (NB_CLIENTS + 1) + j)] + " ");
                }
            }
            System.out.println();
        }
    }
}