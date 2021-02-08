package twisk.monde;


public class Guichet extends Etape {

    private final int nbJetons;
    private int numeroGuichet;

    /**
     * Constructeur d'un guichet
     *
     * @param nom le nom du guichet
     */
    public Guichet(String nom) {
        super(nom);
        this.nbJetons = 10;
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