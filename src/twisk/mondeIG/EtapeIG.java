package twisk.mondeIG;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;


public abstract class EtapeIG implements Iterable<EtapeIG>, Serializable {
    private final ArrayList<PointDeControleIG> TabpointDeControleIG;
    private final LinkedList<EtapeIG> ListeSuccesseur;
    private int posX;
    private int posY;
    private String nom;
    private final String identifiant;
    private final int hauteur;
    private final int largeur;
    private boolean estSelectionne;
    private boolean estUneEntree;
    private boolean estUneSortie;

    /**
     * Constructeur d'une EtapeIG
     * @param nom son nom
     * @param idf son identifiant unique
     * @param larg sa largeur
     * @param haut sa longueur
     */
    public EtapeIG(String nom,String idf, int larg, int haut) {
        Random random = new Random();
        TabpointDeControleIG = new ArrayList<>(4);
        ListeSuccesseur = new LinkedList<>();
        this.nom = nom;
        identifiant = idf;
        posX = random.nextInt(600);
        posY = random.nextInt(600);
        hauteur = haut;
        largeur = larg;
        estSelectionne = false;
        estUneEntree = false;
        estUneSortie = false;
        ajouterPointDeControle();
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
     * @return  false est une Activité
     */
    public boolean estUneActivite(){
        return false;
    }

    /**
     * @return false est une ActiviteRestreinte
     */
    public boolean estUneActiviteRestreinte(){
        return false;
    }

    /**
     * @return false est un Guichet
     */
    public boolean estUnGuichet(){
        return false;
    }

    /**
     * retourne le nombre de successeur de l'Etape
     * @return le nombre de successeur de l'Etape
     */
    public int nombreDeSuccesseur(){
        return ListeSuccesseur.size();
    }

    /**
     * ajoute un successeur à l'Etape
     * @param etapeIG l'Etape
     */
    public void ajouterSuccesseur(EtapeIG etapeIG){
        ListeSuccesseur.add(etapeIG);
    }

    /**
     * retourne le nombre de PointDeCOntrole d'une Etape
     * @return la taille du tableau de PointDeControle en mémoire
     */
    public int nombreDePointDeControle(){ return TabpointDeControleIG.size(); }


    /**
     * supprimer un successeur spécifique
     * @param succ le successeur
     */
    public void supprimerSuccesseur(EtapeIG succ){
        ListeSuccesseur.remove(succ);
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
     * La fonction vérifie que l'Entrée en bien relié à la sortie
     * @param etapeIG l'Etape de départ
     * @return vrai si est connexe
     */
    public boolean estAccessibleDepuis(EtapeIG etapeIG) {
        if (nom.equals(etapeIG.nom)) {
            return true;
        }
        for (EtapeIG succ : etapeIG) {
            return estAccessibleDepuis(succ);
        }
        return false;
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
     * Repositionne tous les Points de contrôle sur une Etape
     * (est utilisé après avoir déplacé une Etape)
     */
    public void repositionnerTousLesPDC(){
        for(PointDeControleIG points : TabpointDeControleIG){
            points.assignerPosition(points.getPosition());
        }
    }

    /**
     * ajouter les 4 PointDeControles haut,bas,gauche et droite
     * d'une EtapeIG
     */
    private void ajouterTousLesPointsDeControle(String type){
        assert(type.equals("Activite") || type.equals("Guichet")) : "Erreur type inconnu.";
        TabpointDeControleIG.add( new PointDeControleIG(this,"droite"));
        TabpointDeControleIG.add( new PointDeControleIG(this,"gauche"));
        if(type.equals("Activite")){
            TabpointDeControleIG.add( new PointDeControleIG(this,"haut"));
            TabpointDeControleIG.add( new PointDeControleIG(this,"bas"));
        }
    }

    /**
     * retourne un PointDeControle de l'EtapeIG
     * @param position le position du pointDeControle
     * @return un PointdeControle de l'EtapeIG
     */
    public PointDeControleIG getPointDeControle(String position){
        for(PointDeControleIG pointDeControleIG : TabpointDeControleIG){
            if(pointDeControleIG.getPosition().equals(position)){
                return pointDeControleIG;
            }
        }
        return null;
    }


    @Override
    public Iterator<EtapeIG> iterator(){
        return ListeSuccesseur.iterator();
    }

    public Iterator<PointDeControleIG> pointDeControleIGIterator(){ return TabpointDeControleIG.iterator();}
}
