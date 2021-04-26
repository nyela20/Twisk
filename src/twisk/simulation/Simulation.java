package twisk.simulation;

import twisk.exceptionstwisk.ExceptionObjetNonTrouve;
import twisk.monde.Monde;
import twisk.outils.FabriqueNumero;
import twisk.outils.KitC;


public class Simulation {


    private int NB_CLIENTS;
    private final GestionnaireClients gestionnaireClients;

    public Simulation() {
        gestionnaireClients = new GestionnaireClients();
    }


    /**
     * assigner le nombre de client(s) dans le monde
     * @param nbClients le nombre de client
     */
    public void setNbClients(int nbClients) {
        assert (nbClients > 0) : "erreur valeur nbclients.";
        this.NB_CLIENTS = nbClients;
    }

    public native int[] start_simulation(int NB_ETAPES, int NB_GUICHET, int NB_CLIENTS, int[] tabJeton);

    public native int[] ou_sont_les_clients(int NB_ETAPES, int NB_GUICHET);

    public native void nettoyage();

    /**
     * Lance la simulation du monde
     * @param monde le monde à simuler
     */
    public void simuler(Monde monde) throws ExceptionObjetNonTrouve {

        FabriqueNumero.getInstance().incrementerlibrairenum();
        String libnum= String.valueOf(FabriqueNumero.getInstance().getNumerolibrairie());
        System.out.println(monde);
        KitC kitC = new KitC();
        kitC.creerEnvironnement();
        kitC.creerFichier(monde.toC());
        kitC.compiler();
        kitC.construireLaLibrairie();
        System.load("/tmp/twisk/libTwisk"+libnum+".so");


        //--------------Ecriture du code MAIN.C

        int NB_GUICHETS = monde.nbGuichet();
        int NB_ETAPES = monde.nbEtapes();
        int j=0;
        int[] TableauDeJetons = new int[monde.nbGuichet()];
        for( int i=0; i < NB_ETAPES; i++) {
            if (monde.getEtape(monde.getNomNiemeEtape(i)).estUnGuichet()) {
                    TableauDeJetons[j] = monde.getnombreDeJetonsEtape(monde.getNomNiemeEtape(i));
                    j++;
            }
        }
        System.out.println("nb etapes " + NB_ETAPES + " nbguichet "+ NB_GUICHETS +" nbclient " +  NB_CLIENTS );
        for(int i = 0; i< TableauDeJetons.length;i++ ) {
            System.out.println(("tab[" + i + "] = " + TableauDeJetons[i]));
        }
        int[] tableauClientsStep = start_simulation(NB_ETAPES, NB_GUICHETS, NB_CLIENTS, TableauDeJetons);
        //ajouter les clients dans le monde
        gestionnaireClients.setClients(tableauClientsStep);

        //---------- Affiche les clients en début de simulation------------
        System.out.print("les clients : ");
        for (int i = 0; i < tableauClientsStep.length - 1; i++){
            System.out.print(tableauClientsStep[i] + " ");
        }
        System.out.print(tableauClientsStep[NB_CLIENTS - 1] + "\n\n");

        //----------  Affiche les clients au fur et à mesure de la simulation------------
        boolean findeBoucle = false;
        while (!findeBoucle) {
            if (ou_sont_les_clients(NB_ETAPES, NB_CLIENTS)[monde.getSasSortieNumeroEtape() * NB_CLIENTS + 1] == NB_CLIENTS) {
                findeBoucle = true;
            }
            int[] tabEmplaceClients = ou_sont_les_clients(NB_ETAPES, NB_CLIENTS);
            affichage_tab_deplacementClients(tabEmplaceClients, NB_ETAPES, monde);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        nettoyage();
    }


    /**
     * la fonction affiche les Etapes et la positions des clients dans le monde
     * @param tab       un tableau contenant le numéro des clients
     * @param nb_etapes le nombre d'étape(s) dans le monde
     * @param monde     le monde
     * @throws ExceptionObjetNonTrouve lance un exception dans le getter
     */
    public void affichage_tab_deplacementClients(int[] tab, int nb_etapes, Monde monde){
        for (int i = 0; i < nb_etapes; i++) {
            System.out.print("Etape " + i + " (" + monde.getNomNiemeEtape(i) + ") " + tab[(i * (NB_CLIENTS + 1))] + " clients :\t");
            for (int j = 1; j <= tab[(i * (NB_CLIENTS + 1))]; j++) {
                if (tab[(i * (NB_CLIENTS + 1) + j)] != 0) {
                    Client clientinterm = gestionnaireClients.getClient(tab[(i * (NB_CLIENTS + 1) + j)]);
                    clientinterm.allerA(monde.getEtape(monde.getNomNiemeEtape(i)), 0);
                    System.out.print(tab[(i * (NB_CLIENTS + 1) + j)] + " ");
                  //System.out.println("\nle client numéro: " + tab[(i * (NB_CLIENTS + 1) + j)] + " ");
                  //System.out.println("est à l'étape " + clientinterm.getEtapeClient());
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}