package twisk.mondeIG;

import java.util.Random;

public class GuichetIG extends EtapeIG{

    private int NombreDeJetons;

    /**
     * Constructeur d'un ActiviteIG
     * @param nom son nom
     * @param idf son identifiant
     * @param larg sa largeur
     * @param haut sa longueur
     */
    public GuichetIG(String nom,String idf, int larg, int haut) {
        super(nom,idf, larg, haut);
        Random random = new Random();
        NombreDeJetons = random.nextInt(3)+2;
    }

    /**
     * assigne le nombre de jetons du guichet
     * @param nombreDeJetons nbjetons
     */
    public void setNombreDeJetons(int nombreDeJetons){
        this.NombreDeJetons = nombreDeJetons;
    }

    /**
     * retourne le nombre de jetons du guichet
     * @return le nombre de jetons du guichet
     */
    public int getNombreDeJetons(){
        return this.NombreDeJetons;
    }

    @Override
    public boolean estUnGuichet() {
        return true;
    }
}
