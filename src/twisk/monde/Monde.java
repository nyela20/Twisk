package twisk.monde;

import twisk.outils.FabriqueNumero;

import java.util.Iterator;

public class Monde implements Iterable<Etape> {

    private final Etape sasSortie;
    private final Etape sasEntree;
    private final GestionnaireEtapes gestioEtapes;


    /**
     * Constructeur d'un Monde
     */
    public Monde() {
        FabriqueNumero.getInstance().reset();
        this.sasEntree = new SasEntree();
        this.gestioEtapes = new GestionnaireEtapes();
        this.sasSortie = new SasSortie();
    }

    /**
     * Défini les entrées du monde
     *
     * @param etapes les entrées du monde
     */
    public void aCommeEntree(Etape... etapes) {
        sasEntree.ajouterSuccesseur(etapes);
    }

    /**
     * Défini les sorties du monde
     *
     * @param etapes les sortie
     */
    public void aCommeSortie(Etape... etapes) {
        for (Etape e : etapes) {
            assert (e.estUneActivite()) : "un guichet comme sortie est impossible.";
            e.ajouterSuccesseur(sasSortie);
        }
    }

    /**
     * Ajoute successivement des Etapes au Monde
     *
     * @param etapes les Etape  à ajoutés
     */
    public void ajouter(Etape... etapes) {
        gestioEtapes.ajouter(etapes);
    }

    /**
     * retourne le nombre d'Etape dans le monde
     *
     * @return le nb d'Etape dans le monde
     */
    public int nbEtapes() {
        return gestioEtapes.nbEtapes() + 2;
    }

    /**
     * retourne le nombre de Guichet dans le monde
     *
     * @return le nb de Guichet dans le monde
     */
    public int nbGuichet() {
        return gestioEtapes.nbGuichet();
    }

    /**
     * le monde sous code C
     *
     * @return code C
     */
    public String toC() {
        StringBuilder affichage = new StringBuilder();

        //Ecriture des includes
        affichage.append("#include<stdio.h>\n");
        affichage.append("#include<stdlib.h>\n");
        affichage.append("#include\"def.h\"\n\n");

        //ecriture des defines des etapes
        affichage.append("#define ").append(sasEntree.getNom()).append(" ").append(sasEntree.getNumeroEtape()).append("\n\n");
        for (Etape e : gestioEtapes) {
            affichage.append("#define ").append(e.getNom()).append(" ").append(e.getNumeroEtape()).append("\n");
        }
        affichage.append("\n");

        affichage.append("#define ").append(sasSortie.getNom()).append(" ").append(sasSortie.getNumeroEtape()).append("\n\n");


        //Ecritures des defines des numero des semamphores
        System.out.println("");
        for (Etape e : gestioEtapes) {
            if (e.estUnGuichet()) {
                affichage.append("#define ");
                affichage.append("num_sem");
                affichage.append(((Guichet) e).getNoSemaphore()).append(" ");
                affichage.append(((Guichet) e).getNoSemaphore()).append("\n");
            }
        }

        //Ecriture de la fonction Simuler
        affichage.append("void simulation(int ids){\n");
        affichage.append(sasEntree.toC());
        Iterator<Etape> it = gestioEtapes.iterator();
        while (it.hasNext()) {
            Etape e = it.next();
            affichage.append(e.toC());
            if (e.estUnGuichet()) {
                it.next();
            }
        }
        affichage.append("}");

        //Fin
        return affichage.toString();
    }

    /**
     * Affichage du monde
     *
     * @return affichge du monde en string
     */
    @Override
    public String toString() {
        return sasEntree + "\n\n" +
                gestioEtapes + "\n" +
                sasSortie;
    }

    /**
     * retourne l'itérateur de GestionEtapes
     *
     * @return Iterator<Etape>
     */
    @Override
    public Iterator<Etape> iterator() {
        return gestioEtapes.iterator();
    }

    /**
     * la fonction retourne le numéroD'Etape du sasDeSortie
     * utile pour atteindre la condition de fin
     * de la boucle while dans la classe Simulation
     */
    public int getSasSortieNumeroEtape() {
        return this.sasSortie.getNumeroEtape();
    }
}
