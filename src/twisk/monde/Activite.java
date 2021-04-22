package twisk.monde;

import java.util.Iterator;

public class Activite extends Etape {

    private final int temps;
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
        if (this.nbSuccesseurs() == 1) {
            Etape succ = iterator().next();
            affichage.append("\ndelai(" + temps + "," + ecartTemps + ");\n");
            affichage.append("transfert(" + getNom() + "," + succ.getNom() + ");\n" + succ.toC());
        }
        //bifurcation
        if (this.nbSuccesseurs() > 1) {
            Iterator<Etape> iterator = this.iterator();
            affichage.append("\nint nb = (int)((rand()/(float) RAND_MAX) *" + nbSuccesseurs() + ");\n");
                affichage.append("switch(nb){\n");
                    for (int i = 0; i < nbSuccesseurs(); i++) {
                        Etape succ = iterator.next();
                        affichage.append("case " + i + ":\n");
                        affichage.append("delai(" + temps + "," + ecartTemps + ");\n");
                        affichage.append("transfert(" + getNom() + "," + succ.getNom() + ");" + succ.toC() + "break;\n");
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