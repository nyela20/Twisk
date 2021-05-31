package twisk.simulation;

import twisk.monde.Guichet;
import twisk.monde.Monde;
import twisk.mondeIG.MondeIG;
import twisk.mondeIG.SujetObserve;
import twisk.outils.FabriqueNumero;
import twisk.outils.KitC;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;


public class Simulation extends SujetObserve implements Iterable<Client> , Serializable {


    private int nbClients;
    private int[] TabPid = new int[0];
    private final KitC kitC = new KitC();
    private final GestionnaireClients gestionnaireClients;

    public Simulation() {
        super();
        gestionnaireClients = new GestionnaireClients();
    }


    /**
     * assigner le nombre de client(s) dans le monde
     * @param nbClients le nombre de client
     */
    public void setNbClients(int nbClients) {
        assert (nbClients > 0) : "erreur valeur nbclients.";
        this.nbClients = nbClients;
        TabPid = new int[nbClients];
    }

    /**
     * ajoute un observateur
     * @param mondeIG l'observateur
     */
    public void ajouterObservateur(MondeIG mondeIG){ this.ajouter(mondeIG); }

    public native int[] start_simulation(int nbEtapes, int nbGuichet, int nbClients, int[] tabJeton);

    public native int[] ou_sont_les_clients(int nbEtapes, int nbGuichet);

    public native void nettoyage();

    /**
     * Lance la simulation du monde
     * @param monde le monde à simuler
     */
    public void simuler(Monde monde) throws IOException, InterruptedException {
        try {
            FabriqueNumero.getInstance().incrementerlibrairenum();
            String libnum = String.valueOf(FabriqueNumero.getInstance().getNumerolibrairie());
            System.out.println(monde);
            kitC.creerEnvironnement();
            kitC.creerFichier(monde.toC());
            kitC.compiler();
            kitC.construireLaLibrairie();
            System.load("/tmp/twisk/libTwisk" + libnum + ".so");


            int nbGuichets = monde.nbGuichet();
            int nbEtapes = monde.nbEtapes();
            int j = 0;
            int[] TableauDeJetons = new int[monde.nbGuichet()];
            for (int i = 0; i < monde.nbEtapes(); i++) {
                if (monde.getEtape(monde.getNomEtapeDontNumSem(i)).estUnGuichet()) {
                    TableauDeJetons[j] = ((Guichet) monde.getEtape(monde.getNomEtapeDontNumSem(i))).getNombreDeJetons();
                    j++;
                }
            }
            TabPid = start_simulation(nbEtapes, nbGuichets, nbClients, TableauDeJetons);
            gestionnaireClients.setClients(TabPid);
            System.out.print("les clients : ");
            for (int i = 0; i < TabPid.length - 1; i++) {
                System.out.print(TabPid[i] + " ");
            }
            System.out.print(TabPid[nbClients - 1] + "\n\n");
            boolean findeBoucle = false;
            while (!findeBoucle) {
                if (ou_sont_les_clients(nbEtapes, nbClients)[monde.getSasSortieNumeroEtape() * nbClients + 1] == nbClients) {
                    findeBoucle = true;
                }
                int[] tabEmplaceClients = ou_sont_les_clients(nbEtapes, nbClients);
                affichage_tab_deplacementClients(tabEmplaceClients, nbEtapes, monde);
                Thread.sleep(1000);
                notifierObservateur();
            }
        } catch (Exception e) {
            kitC.kill(TabPid);
            nettoyage();
            notifierObservateur();
        }
    }



    /**
     * la fonction affiche les Etapes et la positions des clients dans le monde
     * @param TabPid       un tableau contenant le numéro des clients
     * @param nb_etapes le nombre d'étape(s) dans le monde
     * @param monde     le monde
     */

    public void affichage_tab_deplacementClients(int[] TabPid, int nb_etapes, Monde monde) {
        for (int i = 0; i < nb_etapes; i++) {
            int nombreDeClientsDansEtape = TabPid[(i * (nbClients + 1))];
            System.out.print("Etape " + i + " (" + monde.getNomEtapeDontNumSem(i) + ") " + nombreDeClientsDansEtape + " clients :\t");
            for (int j = 1; j <= TabPid[(i * (nbClients + 1))]; j++) {
                if (TabPid[(i * (nbClients + 1) + j)] != 0) {
                    Client clientinterm = gestionnaireClients.getClient(TabPid[(i * (nbClients + 1) + j)]);
                    clientinterm.allerA(monde.getEtape(monde.getNomEtapeDontNumSem(i)), j);
                    System.out.print(TabPid[(i * (nbClients + 1) + j)] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public Iterator<Client> iterator() {
        return gestionnaireClients.iterator();
    }
}