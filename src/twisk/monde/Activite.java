package twisk.monde;

import java.util.Iterator;

public class Activite extends Etape {

    private int temps;
    private final int ecartTemps;

    /**
     * Constructeur d'une Activité
     *
     * @param nom le nom de l'Activité
     */
    public Activite(String nom) {
        super(nom);
        this.temps = 15;
        this.ecartTemps = 10;
    }

    /**
     * Constructeur d'une Activité
     *
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
     * retourne l'ecart temps
     *
     * @return ecaartTemps
     */
    public int getEcartTemps() {
        return ecartTemps;
    }

    /**
     * retourne temps
     *
     * @return temps
     */
    public int getTemps() {
        return temps;
    }

    /**
     * retourne un booléen
     *
     * @return vrai si c'est une Activité, sinon faux
     */
    @Override
    public boolean estUneActivite() {
        return true;
    }

    /**
     * retourne un booléen
     *
     * @return vrii si c'est un guichet, sinon faux
     */
    @Override
    public boolean estUnGuichet() {
        return false;
    }

    /**
     * la fonction assigne un temps à l'Activité
     * @param nouveauTemps le nouveau temps
     */
    public void setTemps(int nouveauTemps){
        temps = nouveauTemps;
    }

    @Override
    public boolean estUneActiviteRestreinte() {
        return false;
    }

    public void ajouterSuccesseur(Etape... etapes) {
        super.ajouterSuccesseur(etapes);
    }

    /**
     * retourne une etape en langugae C
     */
    public String toC() {
        StringBuilder affichage = new StringBuilder();
        if (nbSuccesseurs() == 1) {
            Etape succ = iterator().next();
            affichage.append("\ndelai(").append(temps).append(",").append(ecartTemps).append(");\n");
            affichage.append("transfert(").append(getNom()).append(",").append(succ.getNom()).append(");\n").append(succ.toC());
        }
        //bifurcation
        if (nbSuccesseurs() > 1) {
            Iterator<Etape> iterator = this.iterator();
            affichage.append("\nint nb = (int)((rand()/(float) RAND_MAX) *").append(nbSuccesseurs()).append(");\n");
                affichage.append("switch(nb){\n");
                    for (int i = 0; i < nbSuccesseurs(); i++) {
                        Etape succ = iterator.next();
                        affichage.append("case ").append(i).append(":\n");
                        affichage.append("delai(").append(temps).append(",").append(ecartTemps).append(");\n");
                        affichage.append("transfert(").append(getNom()).append(",").append(succ.getNom()).append(");").append(succ.toC()).append("break;\n");
                    }
                affichage.append("}\n");
        }
        return affichage.toString();
    }

        /**
         * retourne le nom de l'activité
         * @return String, le nom de l'activité
         */
        @Override
        public String toString(){
            return super.toString();
        }
    }