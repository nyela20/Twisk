package twisk.monde;

public class ActiviteRestreinte extends Activite {

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

    public ActiviteRestreinte(String nom){
        super(nom);
    }


    @Override
    public int getTemps() {
        return super.getTemps();
    }

    @Override
    public int getEcartTemps() {
        return super.getEcartTemps();
    }

    @Override
    public boolean estUneActivite() {
        return false;
    }

    public void ajouterSuccesseur(Etape... etapes){
        super.ajouterSuccesseur(etapes);
    }

    public boolean estUneActiviteRestreinte(){
        return true;
    }

    public String toC(){
        return iterator().next().toC();
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
