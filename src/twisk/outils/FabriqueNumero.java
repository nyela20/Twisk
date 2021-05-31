package twisk.outils;

public class FabriqueNumero {

    private static final FabriqueNumero instance = new FabriqueNumero();
    private int cptEtape;
    private int cptSemaphore;
    private int libnum;

    /**
     * Constructeur du Singleton
     */
    private FabriqueNumero() {
        cptEtape = 0;
        cptSemaphore = 1;
        libnum = 1;
    }

    /**
     * retourne une instance unique de FabriqueNumero
     *
     * @return FabriqueNumero
     */
    public static FabriqueNumero getInstance() {
        return instance;
    }

    /**
     * Retourne la valeur du compteur d'Etape
     *
     * @return cptEtape
     */
    public int getNumeroEtape() {
        assert (cptEtape >= 0) : "Le compteur ne doit pas être négatif.";
        cptEtape++;
        return cptEtape - 1;
    }


    /**
     * la fonction incrément +1 la valeur du numéro de librairie
     */
    public void incrementerlibrairenum(){
        libnum++;
    }

    /**
     * retourne un numéro unique pour la librairie
     * @return un numéro unique pour la libraire
     */
    public int getNumerolibrairie(){
        assert(libnum > 0) :"le numéro de librairie est inférieur/égale à 0";
        return libnum;
    }

    /**
     * retourne la valeur du compteur Sémaphore
     *
     * @return cptSemaphore
     */
    public int getNumeroSemaphore() {
        assert (cptSemaphore > 0) : "Le compteur ne doit pas être inférieure à 1.";
        cptSemaphore++;
        return cptSemaphore - 1;
    }

    /**
     * La fonction réinitialise la valeur
     * de cptSemaphore à 0
     * de cptEtape à 0
     */
    public void reset() {
        cptEtape = 0;
        cptSemaphore = 1;
    }
}
