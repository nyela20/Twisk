package twisk.mondeIG;


import twisk.outils.FabriqueIdentifiant;

import java.awt.*;
import java.util.Objects;

public class PointDeControleIG  {
    private final Point centre = new Point();
    private final String identifiant;
    private final EtapeIG etape;
    private final String position;
    private boolean estSelectionne;

    /**
     * Constructeur d'un PointDeControleIG
     * @param etp l'Etape rataché à cet pointDeControle
     * @param pos la position sur cet EtapeIG
     */
    public PointDeControleIG(EtapeIG etp, String pos){
        position = pos;
        etape = etp;
        identifiant = FabriqueIdentifiant.getInstance().getPointDeControleIdentifiant();
        estSelectionne = false;
        assignerPosition(pos);
    }


    /**
     * assigne une position en fonction du paramètre
     * @param pos la position sur l'Etape
     */
    public void assignerPosition(String pos){
        switch(pos){
            case "haut" :
                centre.x = etape.getPosX()+etape.getLargeur()/2;
                centre.y = etape.getPosY();
                break;

            case "bas" :
                centre.x = etape.getPosX()+etape.getLargeur()/2;
                centre.y = etape.getPosY()+etape.getHauteur();
                break;

            case "droite" :
                centre.x = etape.getPosX()+etape.getLargeur();
                centre.y = etape.getPosY()+etape.getHauteur()/2;
                break;

            case "gauche" :
                centre.x = etape.getPosX();
                centre.y = etape.getPosY()+etape.getHauteur()/2;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + pos);
        }
    }

    /**
     * retourne vrai si le PointDeControle est sur l'Etape donnée en paramètre
     * @param pdc le pointDeControle de référence
     * @return vrai si le PointDeControle est sur l'Etape donnée en paramètre, sinon false
     */
    public boolean estSurLaMemeEtapeQue(PointDeControleIG pdc){
        return etape.getIdentifiant().equals(pdc.etape.getIdentifiant());
    }

    /**
     * modifie l'Etat de séléction d'un PointDeControle
     * @param select true si estSelectionné sinon false
     */
    public void setEstSelectionne(boolean select){
        estSelectionne = select;
    }

    /**
     * retourne l'Etat d'un poinDeControle
     * @return true si est Séléctionnée sinon false
     */
    public boolean estSelectionne(){
        return estSelectionne;
    }

    /**
     * retourne l'abscisse du PointDeControle
     * @return x
     */
    public double getx(){
        return centre.x;
    }

    /**
     * retourne l'ordonnée du PointDeControle
     * @return y
     */
    public double gety(){
        return centre.y;
    }

    /**
     * retourne la position du PointDeControle
     * "haut, "bas, "gauche", "droite"
     * @return position
     */
    public String getCote(){
         return position;
    }

    /**
     * retourne l'Etape rattaché au PointDeControle
     * @return etape
     */
    public EtapeIG getEtapeIG(){
        return etape;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PointDeControleIG)) return false;
        PointDeControleIG that = (PointDeControleIG) o;
        return estSelectionne == that.estSelectionne && centre.equals(that.centre) && identifiant.equals(that.identifiant) && etape.equals(that.etape) && position.equals(that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(centre, identifiant, etape, position, estSelectionne);
    }
}
