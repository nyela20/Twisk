package twisk.monde;


import twisk.outils.FabriqueNumero;

import java.util.Iterator;

public abstract class Etape implements Iterable<Etape> {

    private final int numeroEtape;
    private final String nom;
    private final GestionnaireSuccesseurs gestionSucc;
    private int nombreDeClients;


    /**
     * Constructeur d'une Etape
     *
     * @param nom de l'Etape
     */
    public Etape(String nom) {
        this.nom = nom;
        this.gestionSucc = new GestionnaireSuccesseurs();
        this.numeroEtape = FabriqueNumero.getInstance().getNumeroEtape();
        nombreDeClients = 0;
    }

    /**
     * assigne un nombre de Clients à une Etape
     * @param nbClients le nombre de Clients
     */
    public void setNbClients(int nbClients){
        nombreDeClients = nbClients;
    }

    /**
     * retourne le nomre de clients dans un Etape
     * @return le nombre de clients dans une Etape
     */
    public int getNombreDeClients() {
        return nombreDeClients;
    }

    /**
     * Ajoute un successeur à une Etape
     *
     * @param etapes à rajouter
     */

    public void ajouterSuccesseur(Etape... etapes) {
        gestionSucc.ajouter(etapes);
    }

    /**
     * NuEtape
     * @return nuEtape
     */
    public int getNumeroEtape() {
        return numeroEtape;
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
     * @return vrai si un guichet, sinon faux
     */
    public abstract boolean estUnGuichet();

    /**
     * retourne la définition d'une Acti Rest
     * @return vrai si un guichet, sinon faux
     */
    public abstract boolean estUneActiviteRestreinte();

    /**
     * @return le nom de l'etape
     */
    public String getNom(){
        return this.nom;
    }

    public abstract String toC();

    /**
     * retourne le nom de l'Etape
     *
     * @return le nom de l'Etape
     */
    @Override
    public String toString(){
        return nom + " : " +
                nbSuccesseurs() +
                " Successeur " +
                gestionSucc.toString();
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
