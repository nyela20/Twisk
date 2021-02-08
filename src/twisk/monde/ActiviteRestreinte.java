package twisk.monde;

public class ActiviteRestreinte extends Activite{

    /**
     * Constructeur d'une ActiviteRestreinte
     * @param nom
     */
    public ActiviteRestreinte(String nom) {
        super(nom);
    }

    /**
     * Constructeur d'une ActiviteRestreinte
     * @param nom
     * @param temps
     * @param ecartTemps
     */
    public ActiviteRestreinte(String nom, int temps, int ecartTemps) {
        super(nom, temps, ecartTemps);
    }

    /**
     * Affichage de l'ActivitéRestrinte
     * @return String
     */
    @Override
    public String toString() {
        return super.toString();
    }

}
