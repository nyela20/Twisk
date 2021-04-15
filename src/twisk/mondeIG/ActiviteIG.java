package twisk.mondeIG;


public class ActiviteIG extends EtapeIG {

    private int delai;
    private int ecarttemps;
    /**
     * Constructeur d'un ActiviteIG
     * @param nom son nom
     * @param idf son identifiant
     * @param larg sa largeur
     * @param haut sa longueur
     */
    public ActiviteIG(String nom,String idf, int larg, int haut) {
        super(nom,idf, larg, haut);
        delai = 0;
        ecarttemps = 0;
    }

    @Override
    public boolean estUneActivite() {
        return true;
    }

    @Override
    public boolean estUnGuichet() {
        return false;
    }

    /**
     * retourne le Delai assigné à une EtapeIG
     * @return delai
     */
    public int getDelai() {
        return delai;
    }

    /**
     * modifie le délai assigné à une EtapeIG
     * @param nouveaudelai le nouveau délai
     */
    public void setDelai(int nouveaudelai){
        delai = nouveaudelai;
    }

    /**
     * modifie l'Ecart-Temps assigné à une EtapeIG
     * @param nouveauEcartTemps le nouveau Ecart-Temps
     */
    public void setEcarttemps(int nouveauEcartTemps){
        ecarttemps = nouveauEcartTemps;
    }


    /**
     * retourne l'Ecart-Temps assigné o une EtapeIG
     * @return ecarttemps
     */
    public int getEcarttemps() {
        return ecarttemps;
    }
}
