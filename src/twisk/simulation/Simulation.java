package twisk.simulation;

import twisk.monde.Monde;
import twisk.outils.KitC;


public class Simulation {

    public native int[] start_simulation(int NB_ETAPES, int NB_GUICHET, int NB_CLIENTS, int[] tabJeton);

    public native int[] ou_sont_les_clients(int NB_ETAPES, int NB_GUICHET);

    public native void nettoyage();


    public Simulation() {
    }

    public void simuler(Monde monde) {

        System.out.println(monde);

        KitC kitC = new KitC();
        kitC.creerEnvironnement();
        kitC.creerFichier(monde.toC());
        kitC.compiler();
        kitC.construireLaLibrairie();
        System.load("/tmp/twisk/libTwisk.so");


        //--------------Ecriture du cde MAIN.C

        int NB_ETAPES = monde.nbEtapes();
        int NB_GUICHET = monde.nbGuichet();
        int NB_CLIENTS = 10;

        void affichage_tab ( int *tab,int nb_etapes)
        {
            for (int i = 0; i < nb_etapes; i++) {
                System.out.println("etape %d , nombre de clients %d: ", i, tab[(i * (NB_CLIENTS + 1))]);

                for (int j = 1; j <= tab[(i * (NB_CLIENTS + 1))]; j++) {
                    if (tab[(i * (NB_CLIENTS + 1) + j)] != 0) {
                        printf("%d ,", tab[(i * (NB_CLIENTS + 1) + j)]);
                    }
                }

                System.out.println();
            }
        }

        int main ( int argc, char **argv)
        {

            System.out.println();
            System.out.println();


            int tabJeton[] = new int[2];
            tabJeton[0] = 3;


            int *tabClientPID = start_simulation(NB_ETAPES, NB_GUICHET, NB_CLIENTS, tabJeton);
            int *tabClientZone = ou_sont_les_clients(NB_ETAPES, NB_CLIENTS);

            //affichage des PID
            System.out.println("les PID des clients sont : ");
            for (int i = 0; i < NB_CLIENTS; i++) {
                printf("%d, ", tabClientPID[i]);
            }
            System.out.println();
            System.out.println();

            while (tabClientZone[(NB_ETAPES - 1) * (NB_CLIENTS + 1)] != NB_CLIENTS) {
                tabClientZone = ou_sont_les_clients(NB_ETAPES, NB_CLIENTS);
                affichage_tab(tabClientZone, NB_ETAPES);
                System.out.println();
                // sleep(1);
            }


        }

    }
