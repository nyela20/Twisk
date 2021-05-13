package twisk.simulation;

import javafx.concurrent.Task;
import twisk.exceptionstwiskIG.ExceptionObjetNonTrouve;
import twisk.monde.Monde;
import twisk.mondeIG.SujetObserve;
import twisk.outils.FabriqueNumero;
import twisk.outils.GestionnaireThreads;
import twisk.outils.KitC;

import java.util.Iterator;


public class Simulation extends SujetObserve implements Iterable<Client>{


    private int nbClients;
    private final GestionnaireClients gestionnaireClients;

    public Simulation() {
        super();
        gestionnaireClients = new GestionnaireClients();
    }

    @Override
    public Iterator<Client> iterator() {
        return gestionnaireClients.iterator();
    }


    /**
     * assigner le nombre de client(s) dans le monde
     * @param nbClients le nombre de client
     */
    public void setNbClients(int nbClients) {
        assert (nbClients > 0) : "erreur valeur nbclients.";
        this.nbClients = nbClients;
    }

    public native int[] start_simulation(int nbEtapes, int nbGuichet, int nbClients, int[] tabJeton);

    public native int[] ou_sont_les_clients(int nbEtapes, int nbGuichet);

    public native void nettoyage();

    /**
     * Lance la simulation du monde
     * @param monde le monde à simuler
     */
    public void simuler(Monde monde) throws ExceptionObjetNonTrouve {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                try {
                    FabriqueNumero.getInstance().incrementerlibrairenum();
                    String libnum = String.valueOf(FabriqueNumero.getInstance().getNumerolibrairie());
                    System.out.println(monde);
                    KitC kitC = new KitC();
                    kitC.creerEnvironnement();
                    kitC.creerFichier(monde.toC());
                    kitC.compiler();
                    kitC.construireLaLibrairie();
                    System.load("/tmp/twisk/libTwisk" + libnum + ".so");


                    //--------------Ecriture du code MAIN.C

                    int nbGuichets = monde.nbGuichet();
                    int nbEtapes = monde.nbEtapes();
                    int j = 0;
                    int[] TableauDeJetons = new int[monde.nbGuichet()];
                    for (int i = 0; i < nbEtapes; i++) {
                        if (monde.getEtape(monde.getNomNiemeEtape(i)).estUnGuichet()) {
                            TableauDeJetons[j] = monde.getnombreDeJetonsEtape(monde.getNomNiemeEtape(i));
                            j++;
                        }
                    }
                    System.out.println("nb etapes " + nbEtapes + " nbguichet " + nbGuichets + " nbclient " + nbClients);
                    for (int i = 0; i < TableauDeJetons.length; i++) {
                        System.out.println(("tab[" + i + "] = " + TableauDeJetons[i]));
                    }
                    int[] tableauClientsStep = start_simulation(nbEtapes, nbGuichets, nbClients, TableauDeJetons);
                    //ajouter les clients dans le monde
                    gestionnaireClients.setClients(tableauClientsStep);

                    //---------- Affiche les clients en début de simulation------------
                    System.out.print("les clients : ");
                    for (int i = 0; i < tableauClientsStep.length - 1; i++) {
                        System.out.print(tableauClientsStep[i] + " ");
                    }
                    System.out.print(tableauClientsStep[nbClients - 1] + "\n\n");

                    //----------  Affiche les clients au fur et à mesure de la simulation------------
                    boolean findeBoucle = false;
                    while (!findeBoucle) {
                        if (ou_sont_les_clients(nbEtapes, nbClients)[monde.getSasSortieNumeroEtape() * nbClients + 1] == nbClients) {
                            findeBoucle = true;
                        }
                        int[] tabEmplaceClients = ou_sont_les_clients(nbEtapes, nbClients);
                        affichage_tab_deplacementClients(tabEmplaceClients, nbEtapes, monde);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        notifierObservateur();
                    }
                    nettoyage();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return null;
            }
        };
        GestionnaireThreads.getInstance().lancer(task);
    }


    /**
     * la fonction affiche les Etapes et la positions des clients dans le monde
     * @param tab       un tableau contenant le numéro des clients
     * @param nb_etapes le nombre d'étape(s) dans le monde
     * @param monde     le monde
     */

    public void affichage_tab_deplacementClients(int[] tab, int nb_etapes, Monde monde){
        for (int i = 0; i < nb_etapes; i++) {
            int nombreDeClientsDansEtape = tab[(i * (nbClients + 1))];
            System.out.print("Etape " + i + " (" + monde.getNomNiemeEtape(i) + ") " + nombreDeClientsDansEtape + " clients :\t");
            for (int j = 1; j <= tab[(i * (nbClients + 1))]; j++) {
                if (tab[(i * (nbClients + 1) + j)] != 0) {
                    Client clientinterm = gestionnaireClients.getClient(tab[(i * (nbClients + 1) + j)]);
                    clientinterm.allerA(monde.getEtape(monde.getNomNiemeEtape(i)), 0);
                    System.out.print(tab[(i * (nbClients + 1) + j)] + " ");
                }
            }
            System.out.println();
            monde.getEtape(monde.getNomNiemeEtape(i)).setNbClients(nombreDeClientsDansEtape);
        }
        System.out.println();
    }




}