package twisk.monde;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class GestionnaireEtapes implements Iterable<Etape> {

    private final LinkedList<Etape> gestionEtapes;

    /**
     * Constructeur d'un GestionnaireEtapes
     */
    public GestionnaireEtapes() {
        gestionEtapes = new LinkedList<>();
    }

    /**
     * ajoute n Etape dans la liste chainées
     * du GestionnaireEtapes
     *
     * @param etapes nombre d'etapes
     */
    public void ajouter(Etape... etapes) {
        this.gestionEtapes.addAll(Arrays.asList(etapes));
    }

    /**
     * retourne le nombre d'Etape du GestionnaireEtapes
     *
     * @return int le nb d'Etape du GestionnaireEtapes
     */
    public int nbEtapes() {
        return gestionEtapes.size();
    }

    /**
     * retourne le nombre de Guichet qu'il y a dans le GestionnaireEtapes
     *
     * @return int le nb de Guichet présent dans le GestionnaireEtapes
     */

    public int nbGuichet() {
        int compt = 0;
        for (Etape e : this.gestionEtapes) {
            if (e.estUnGuichet()) {
                compt++;
            }
        }
        return compt;
    }

    @Override
    public String toString() {
        StringBuilder affichage = new StringBuilder(100);

        for(Etape e : gestionEtapes){
            affichage.append(e.toString());
            affichage.append("\n");
        }

        return affichage.toString();
    }

    /**
     * Afficage du GestionnaireEtapes
     *
     * @return String
     */
    @Override
    public Iterator<Etape> iterator() {
        return gestionEtapes.iterator();
    }
}
