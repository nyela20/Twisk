package twisk.mondeIG;

import java.util.Random;

public class GuichetIG extends EtapeIG{

    private int NombreDeJetons;
    private boolean sensDeCirculation; //true :gauche -> droite, false : droite -> gauche

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
        NombreDeJetons = random.nextInt(8)+1;
        sensDeCirculation = true;
    }

    /**
     * retourne vrai si le sens de circulation du guichet
     * est de gauche à droite
     * @return vrai si le sens de circulation du guichet est de gauche à droite, sinon faux
     */
    public boolean CirculationDeGaucheADroite(){
        return sensDeCirculation;
    }

    /**
     * retourne vrai si le sens de circulation du guichet
     * est de droite à gauche
     * @return vrai si le sens de circulation du guichet est de droite à gauche, sinon faux
     */
    public boolean CirculationDeDroiteAGauche(){
        return !sensDeCirculation;
    }

    /**
     * assigne un sens de Circulation au guichet
     * @param entree reçoit la valeur "gauche" ou "droite"
     * @param sortie reçoit la valeur "droite" ou "gauche"
     */
    public void assignerSensDeCirculation(String entree,String sortie){
        if(entree.equals("gauche") && sortie.equals("droite")){
            sensDeCirculation = true;
        }
        if(entree.equals("droite") && sortie.equals("gauche")){
            sensDeCirculation = false;
        }
    }

    /**
     * assigne le nombre de jetons du guichZet
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
