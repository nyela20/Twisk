package twisk.monde;

public class Activite extends Etape {

    private final int temps;
    private final int ecartTemps;

    /**
     * Constructeur d'une Activité
     * @param nom le nom de l'Activité
     */
    public Activite(String nom) {
        super(nom);
        this.temps = 15;
        this.ecartTemps = 10;
    }

    /**
     * Constructeur d'une Activité
     * @param nom        le nom de l'activité
     * @param temps      le temps de l'activité
     * @param ecartTemps l'ecart-temps de l'activité
     */
    public Activite(String nom, int temps, int ecartTemps) {
        super(nom);
        this.ecartTemps = ecartTemps;
        this.temps = temps;
    }

    /**
     * retourne un booléen
     * @return vrai si c'est une Activité, sinon faux
     */
    @Override
    public boolean estUneActivite() {
        return true;
    }

    /**
     * retourne un booléen
     * @return vrii si c'est un guichet, sinon faux
     */
    @Override
    public boolean estUnGuichet() {
        return false;
    }

    /**
     * retourne le nom de l'activité
     * @return String, le nom de l'activité
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
