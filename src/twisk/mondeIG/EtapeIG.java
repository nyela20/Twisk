package twisk.mondeIG;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public abstract class EtapeIG implements Iterable<PointDeControleIG>{
    private final ArrayList<PointDeControleIG> TabPointsDC;
    private int posX;
    private int posY;
    private String nom;
    private final String identifiant;
    private final int hauteur;
    private final int largeur;
    private boolean estSelectionne;
    private boolean estUneEntree;
    private boolean estUneSortie;
    private String Couleur;

    /**
     * Constructeur d'une EtapeIG
     * @param nom son nom
     * @param idf son identifiant unique
     * @param larg sa largeur
     * @param haut sa longueur
     */
    public EtapeIG(String nom,String idf, int larg, int haut) {
        Random random = new Random();
        TabPointsDC = new ArrayList<>(4);
        this.nom = nom;
        identifiant = idf;
        posX = random.nextInt(600);
        posY = random.nextInt(600);
        hauteur = haut;
        largeur = larg;
        estSelectionne = false;
        estUneEntree = false;
        estUneSortie = false;
        Couleur = "blanc";
        ajouterPointDeControle();
    }

    /**
     * ajoute un PointDeControle à l'Etape
     */
    public void ajouterPointDeControle(){
        if(this.estUnGuichet()) {
            ajouterTousLesPointsDeControle("Guichet");
        }
        if(this.estUneActivite()) {
            ajouterTousLesPointsDeControle("Activite");
        }
    }

    /**
     * Déplace une étape d'un point à un autre
     * @param x le point d'abscisse
     * @param y le point d'ordonnée
     */
    public void deplacerEtape(double x,double y){
            posX = (int) x;
            posY = (int) y;
            repositionnerTousLesPDC();
    }

    /**
     * Repositionne tous les Points de contrôle sur une Etape
     * (est utilisé après avoir déplacé une Etape)
     */
    public void repositionnerTousLesPDC(){
        for(PointDeControleIG points : TabPointsDC){
            points.assignerPosition(points.getCote());
        }
    }

    /**
     * ajouter les 4 PointDeControles haut,bas,gauche et droite
     * d'une EtapeIG
     */
    private void ajouterTousLesPointsDeControle(String type){
        assert(type.equals("Activite") || type.equals("Guichet")) : "Erreur type inconnu.";
        TabPointsDC.add( new PointDeControleIG(this,"droite"));
        TabPointsDC.add( new PointDeControleIG(this,"gauche"));
        if(type.equals("Activite")){
            TabPointsDC.add( new PointDeControleIG(this,"haut"));
            TabPointsDC.add( new PointDeControleIG(this,"bas"));
        }
    }

    /**
     * assigne une EtapeIG en entrée ou inversement
     * @param set true pour assigner sinon false
     */
    public void setEstUneEntree(boolean set){
        estUneEntree = set;
    }

    /**
     * assigne une EtapeIG en sortie ou inversement
     * @param set true pour assigner sinon false
     */
    public void setEstUneSortie(boolean set){
        estUneSortie = set;
    }

    /**
     * retourne true si c'est une Entrée sinon false
     * @return estUneEntree
     */
    public boolean estUneEntree(){
        return estUneEntree;
    }

    /**
     * retourne true si c'est une Sortie sinon false
     * @return estUneSortie
     */
    public boolean estUneSortie(){
        return estUneSortie;
    }

    @Override
    public Iterator<PointDeControleIG> iterator() {
        return TabPointsDC.iterator() ;
    }

    /**
     * renomme une EtapeIG
     * @param n le nouveau nom
     */
    public void renommer(String n){
        nom = n;
    }

    /**
     * retourn l'identifiant unique de l'EtapeIG
     * @return identifiant
     */
    public String getIdentifiant(){
        return identifiant ;
    }

    /**
     * modifie l'état d'un EtapeIG,
     * @param bool true si on veut que l'Etape soit séléctionné sinon false
     */
    public void setEstSelectionne(boolean bool){
        estSelectionne = bool;
    }

    /**
     * retourne true si l'EtapeIG est séléctionné sinon false
     * @return estSelectionne
     */
    public boolean estSelectionne(){
        return estSelectionne;
    }

    /**
     * retourne l'abscisse de l'EtapeIG
     * @return posX
     */
    public int getPosX(){
        return posX;
    }

    /**
     * retourne l'ordonnée de l'EtapeIG
     * @return posY
     */
    public int getPosY(){
        return posY;
    }

    /**
     * retourne la valeur de la largeur d'une EtapeIG
     * @return largeur
     */
    public int getLargeur(){
        return largeur;
    }

    /**
     * retourne la valeur de la hauteur d'une EtapeIG
     * @return hauteur
     */
    public int getHauteur(){
        return hauteur;
    }

    /**
     * retourne le nom d'une EtapeIG
     * @return nom
     */
    public String getNom(){
        return nom;
    }
    

    /**
     * retourne la taille du Tableau de PintDeControle
     * @return TabPointsDC.size()
     */
    public int getTabPointDeControlesize(){
        return TabPointsDC.size();
    }

    /**
     * retourne vrai si Activite sinon false
     * @return  vrai si Activite sinon false
     */
    public abstract boolean estUneActivite();

    /**
     * retourne vrai si Guichet sinon false
     * @return  vrai si Guichet sinon false
     */
    public abstract boolean estUnGuichet();

    /**
     * retourne la couleur de l'Etape
     * @return la couleur de l'Etape
     */
    public String getCouleur(){
        return Couleur;
    }


    /**
     * assigne la couleur d'une Etape
     * @param color la couleur
     */
    public void setCouleur(String color){
        this.Couleur = color;
    }
}
