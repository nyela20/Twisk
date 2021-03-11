package twisk.monde;

import java.util.Iterator;

public class Monde implements Iterable<Etape> {

    private final Etape sasSortie;
    private final Etape sasEntree;
    private final GestionnaireEtapes gestioEtapes;


    /**
     * Constructeur d'un Monde
     */
    public Monde() {
        this.sasEntree = new SasEntree();
        this.sasSortie = new SasSortie();
        this.gestioEtapes = new GestionnaireEtapes();
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
            assert(e.estUneActivite()) : "un guichet comme sortie est impossible.";
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
        return gestioEtapes.nbEtapes();
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
     *
     */

    public String toC(){
        StringBuilder affichage = new StringBuilder();

        for (Etape e : gestioEtapes){
            affichage.append(e.toC());
        }
        return affichage.toString();
    }

    /**
     * Affichage du monde
     *
     * @return affichge du monde en string
     */
    @Override
    public String toString() {

        return sasEntree + "\n" +
                sasSortie + "\n\n" +
                gestioEtapes;
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
}
