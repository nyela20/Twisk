package twisk.monde;

public class ActiviteRestreinte extends Activite {

    /**
     * Constructeur d'une ActiviteRestreinte
     *
     * @param nom le nom de l'activité
     */
    public ActiviteRestreinte(String nom) {
        super(nom);
    }

    /**
     * Constructeur d'une ActiviteRestreinte
     *
     * @param nom        le nom de l'activité
     * @param temps      le temps de l'activité
     * @param ecartTemps l'ecart-temps de l'activité
     */
    public ActiviteRestreinte(String nom, int temps, int ecartTemps) {
        super(nom, temps, ecartTemps);
    }

    /**
     * retourn la definition d'une Activité
     *
     * @return vrai si une activité, sinon faux
     */
    public boolean estUneActiviteRestreinte() {
        return true;
    }

    /**
     * retourne le nom de l'ActivitéRestreinte
     *
     * @return le nom de l'activité en string
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
