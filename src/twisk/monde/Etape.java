package twisk.monde;


import twisk.outils.FabriqueNumero;

import java.util.Iterator;

public abstract class Etape implements Iterable<Etape> {

    private final int numeroEtape;
    private final String nom;
    private final GestionnaireSuccesseurs gestionSucc;


    /**
     * Constructeur d'une Etape
     *
     * @param nom de l'Etape
     */
    public Etape(String nom) {
        this.nom = nom;
        this.gestionSucc = new GestionnaireSuccesseurs();
        this.numeroEtape = FabriqueNumero.getInstance().getNumeroEtape();
    }

    /**
     * Ajoute un successeur à une Etape
     *
     * @param etapes à rajouter
     */
    public void ajouterSuccesseur(Etape... etapes) {
        for (Etape e : etapes) {
            if (this.estUnGuichet() && e.estUneActivite() && (gestionSucc.nbSEtapes() < 1)) {
                gestionSucc.ajouter(e);
            }
            if (this.estUneActivite()) {
                gestionSucc.ajouter(e);
            }
        }
    }

    /**
     * retourne le nombre de successeur(s) d'une Etape
     *
     * @return le nombre de successeur(s) d'une Etape
     */
    public int nbSuccesseurs() {
        return gestionSucc.nbSEtapes();
    }

    /**
     * retourn la definition d'une Activité
     *
     * @return vrai si une activité, sinon faux
     */
    public abstract boolean estUneActivite();

    /**
     * retourne la définition d'un Guichet
     *
     * @return vrai si un guichet, sinon faux
     */
    public abstract boolean estUnGuichet();

    /**
     * retourne le nom de l'Etape
     *
     * @return le nom de l'Etape
     */
    @Override
    public String toString() {
        return this.nom;
    }

    /**
     * Retourne l'itérateur de l'Etape
     *
     * @return l'itérateur de l'Etape
     */
    @Override
    public Iterator<Etape> iterator() {
        return gestionSucc.iterator();
    }
}
