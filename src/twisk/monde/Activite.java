package twisk.monde;

public class Activite extends Etape{

    private final int temps;
    private final int ecartTemps;

    /**
     * Constructeur d'une Activité
     * @param nom
     */
   public Activite(String nom) {
        super(nom);
        this.temps = 15;
        this.ecartTemps = 10;
    }

    /**
     * Constructeur d'une Activité
     * @param nom
     * @param temps
     * @param ecartTemps
     */
   public Activite(String nom,int temps,int ecartTemps) {
        super(nom);
        this.ecartTemps = ecartTemps;
        this.temps = temps;
    }

    /**
     * retourne un booléen
     * @return toujours vrai
     */
    @Override
    public boolean estUneActivite() {
        return true;
    }

    /**
     * retourne un booléen
     * @return toujours faux
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
