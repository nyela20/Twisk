package twisk.monde;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class GestionnaireSuccesseurs implements Iterable<Etape> {

    private final LinkedList<Etape> listEtaps;

    public GestionnaireSuccesseurs() {
        listEtaps = new LinkedList<>();
    }

    public void ajouter(Etape... etapes) {
        listEtaps.addAll(Arrays.asList(etapes));
    }

    public int nbSEtapes() {
        return listEtaps.size();
    }

    @Override
    public Iterator<Etape> iterator() {
        return listEtaps.iterator();
    }

    @Override
    public String toString() {
        StringBuilder affichage = new StringBuilder(100);
        for (Etape e : listEtaps) {
            affichage.append(e);
        }
        return affichage.toString();
    }
}
