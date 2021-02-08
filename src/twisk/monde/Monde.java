package twisk.monde;

import java.util.Iterator;

public class Monde implements Iterable<Etape> {

    private final Etape sasSortie;
    private final Etape sasEntree;
    private final GestionnaireEtapes gestioEtapes;


    /**
     * Constructeur d'un Monde
     */
    public Monde() {
        this.sasEntree = new SasEntree();
        this.sasSortie = new SasSortie();
        this.gestioEtapes = new GestionnaireEtapes();
    }

    /**
     * Défini les entrées du monde
     * ,le SASEntrée
     * @param etapes les entrées du monde
     */
    public void aCommeEntree(Etape... etapes) {
        sasEntree.ajouterSuccesseur(etapes);
    }

    /**
     * Défini les sorties du monde
     * ,le SASSortie
     * @param etapes
     */
    public void aCommeSortie(Etape... etapes) {
        for (Etape e : etapes){
          if(e.estUneActivite()){
              e.ajouterSuccesseur(sasSortie);
          }
        }
    }

    /**
     * Ajoute successivement des Etapes au Monde
     * @param etapes les Etape  à ajoutés
     */
    public void ajouter(Etape...etapes){
        gestioEtapes.ajouter(etapes);
    }

    /**
     * retourne le nombre d'Etape dans le monde
     * @return le nb d'Etape dans le monde
     */
    public int nbEtapes(){
        return gestioEtapes.nbEtapes();
    }

    /**
     * retourne le nombre de Guichet dans le monde
     * @return le nb de Guichet dans le monde
     */
    public int nbGuichet(){
        return gestioEtapes.nbGuichet();
    }

    /**
     * Affichage du Monde (en extension)
     * @return String
     */
    @Override
    public String toString() {

        StringBuilder affichage = new StringBuilder();

        affichage.append(sasEntree).append(": successeur - ");
        for(Etape e: sasEntree){
                    affichage.append(e).append(" ");
                }
        affichage.append("\n");

        affichage.append(sasSortie).append(": -");
        for(Etape e: sasSortie){
            affichage.append(e).append(" ");
        }
        affichage.append("\n");

        for(Etape etape : gestioEtapes){
            affichage.append(etape).append(" ");
            affichage.append("Successeur: ");
            for (Etape j:etape){
                affichage.append(j).append(" - ");
            }
            affichage.append("\n");
        }

        return affichage.toString();
    }

    @Override
    public Iterator<Etape> iterator() {
        return gestioEtapes.iterator();
    }
}
