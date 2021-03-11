package twisk.monde;


import twisk.outils.FabriqueNumero;

public class Guichet extends Etape {

    private final int nbJetons;
    private int numeroGuichet;
    private int noSemaphore;

    /**
     * Constructeur d'un guichet
     *
     * @param nom le nom du guichet
     */
    public Guichet(String nom) {
        super(nom);
        this.nbJetons = 10;
        this.noSemaphore = FabriqueNumero.getInstance().getNumeroSemaphore();
    }

    /**
     * Constructeur d'un guichet
     *
     * @param nom      le nom du guichet
     * @param nbJetons le nombre de jetons dans le guichet
     */
    public Guichet(String nom, int nbJetons) {
        super(nom);
        this.nbJetons = nbJetons;
    }

    /**
     * retourne un booléen
     *
     * @return vrai si c'est un guichet, sinon faux
     */
    @Override
    public boolean estUnGuichet() {
        return true;
    }


    /**
     * retourne un booléen
     *
     * @return vrai si c'est un Activité, sinon faux
     */
    @Override
    public boolean estUneActivite() {
        return false;
    }

    @Override
    public String toC() {


        Etape succ = iterator().next();
        return
                "transfert" + getNom() + 

                "P(ids," + noSemaphore + ")\n" + succ.toC() +
                "\nV(ids," + noSemaphore + ")";


    }

    /**
     * retourne le nom de l'Etape
     *
     * @return le nom du guichet en format string
     */
    @Override
    public String toString() {
        return super.toString();
    }
}