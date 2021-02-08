package twisk.outils;

public class FabriqueNumero {

    private static final FabriqueNumero instance = new FabriqueNumero();
    private int cptEtape;
    private int cptSemaphore;

    private FabriqueNumero(){ }

    /**
     * retourne une instance unique de FabriqueNumero
     * @return
     */
    static FabriqueNumero getInstance(){
        return instance;
    }

    /**
     * retourne la valeur du compteur
     * @return la valeur du compteur
     */
    int getNumeroEtape(){
        assert(cptEtape >= 0) : "Le compteur ne doit pas retourne être négatif.";
        return cptEtape;
    }

    /**
     * retourne la valeur de la sémaphore
     * @return la valeur de la sémaphore
     */
    int getNumeroSemaphore(){
        assert(cptSemaphore > 0) : "Le compteur ne doit pas être inférieure à 1.";
        return cptSemaphore;
    }

    void reset(){
        cptEtape = 0;
    }
}
