package twisk.monde;


import twisk.outils.FabriqueNumero;

public class Guichet extends Etape {

    private final int nbJetons;
    private int noSemaphore;

    /**
     * Constructeur d'un guichet
     *
     * @param nom le nom du guichet
     */
    public Guichet(String nom){
        super(nom);
        this.nbJetons = 3;
        this.noSemaphore = FabriqueNumero.getInstance().getNumeroSemaphore();
    }

    /**
     * Constructeur d'un guichet
     *
     * @param nom      le nom du guichet
     * @param nbJetons le nombre de jetons dans le guichet
     */
    public Guichet(String nom, int nbJetons){
        super(nom);
        this.nbJetons = nbJetons;
        this.noSemaphore = FabriqueNumero.getInstance().getNumeroSemaphore();
    }

    @Override
    public void ajouterSuccesseur(Etape... etapes){
        if (etapes[0].estUneActiviteRestreinte()){
            super.ajouterSuccesseur(etapes[0]);
        }
    }

    public int getNoSemaphore() {
        return noSemaphore;
    }

    public int getNbJetons() {
        return nbJetons;
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

    @Override
    public boolean estUneActiviteRestreinte() {
        return false;
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
     * code C
     *
     * @return code C
     */
    @Override
    public String toC() {
        ActiviteRestreinte succ = (ActiviteRestreinte) iterator().next();
        return "\nP(ids," + "num_sem" + this.getNom() + ");\n" +
                "transfert(" + this.getNom() + "," + succ.getNom() + ");\n" +
                "delai(" + succ.getTemps() + "," + succ.getEcartTemps() + ");\n" +
                "V(ids," + "num_sem" + this.getNom() + ");\n" +
                "transfert(" + succ.getNom() + "," + succ.iterator().next().getNom() + ");\n" + succ.toC();
    }


    /**
     * retourne le nom de l'Etape
     * @return le nom du guichet en format string
     */
    @Override
    public String toString() {
        return super.toString();
    }
}