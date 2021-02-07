package twisk.monde;

import java.util.Iterator;

public class Monde implements Iterable<Etape> {

    private final Etape sasSortie;
    private final Etape sasEntree;
    private final GestionnaireEtapes gestioEtapes;


    public Monde() {
        this.sasEntree = new SasEntree();
        this.sasSortie = new SasSortie();
        this.gestioEtapes = new GestionnaireEtapes();
    }

    public void aCommeEntree(Etape... etapes) {
        sasEntree.ajouterSuccesseur(etapes);
    }

    public void aCommeSortie(Etape... etapes) {
        for (Etape e : etapes){
          if(e.estUneActivite()){
              e.ajouterSuccesseur(sasSortie);
          }
        }
    }

    public void ajouter(Etape...etapes){
        gestioEtapes.ajouter(etapes);
    }

    public int nbEtapes(){
        return gestioEtapes.nbEtapes();
    }

    public int nbGuichet(){
        return gestioEtapes.nbGuichet();
    }

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
