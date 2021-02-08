package twisk.monde;


public class Guichet extends Etape{

    private int nbJetons;
    int numeroGuichet;

    /**
     * Constructeur d'un guichet
     * @param nom
     */
    public Guichet(String nom) {
        super(nom);
        this.nbJetons = 3;
    }

    /**
     * Constructeur d'un guichet
     * @param nom
     * @param nbJetons
     */
    public Guichet(String nom, int nbJetons){
        super(nom);
        this.nbJetons = nbJetons;
    }

    /**
     * retourne un booléen
     * @return toujours vrai
     */
    @Override
    public boolean estUnGuichet() {
        return true;
    }

    /**
     * retourne un booléen
     * @return toujours faux
     */
    @Override
    public boolean estUneActivite() {
        return false;
    }

    /**
     * retourne le nom du guichet
     * @return String , le nom du guichet
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
