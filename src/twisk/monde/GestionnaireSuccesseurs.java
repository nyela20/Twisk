package twisk.monde;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class GestionnaireSuccesseurs implements Iterable<Etape> {

    private final LinkedList<Etape> listEtaps;

    /**
     * Constructeur d'un GestionnaireSuccessuers
     */
    public GestionnaireSuccesseurs() {
        listEtaps = new LinkedList<>();
    }

    /**
     * Ajoute n Etapes dans la liste chainées
     * du GestionnaireSuccesseurs
     * @param etapes les etapes à rajouter
     */
    public void ajouter(Etape... etapes) {
        listEtaps.addAll(Arrays.asList(etapes));
    }

    /**
     * return le nombre D'Etape qu'il y a dans le GestionnaireSuccesseurs
     * @return le nb d'Etape dans le GestionnaireSuccesseurs
     */
    public int nbSEtapes() {
        return listEtaps.size();
    }

    /**
     * retourne l'iterateur du GestionnaireSuccesseurs
     * @return Iterator<Etape>
     */
    @Override
    public Iterator<Etape> iterator() {
        return listEtaps.iterator();
    }

    /**
     * Affichage du GestionnaireSuccesseurs
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder affichage = new StringBuilder(100);
        for (Etape e : listEtaps) {
            affichage.append(e.toString()).append(",");
        }
        return affichage.toString();
    }
}
