package twisk.monde;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class GestionnaireEtapes implements Iterable<Etape> {

    private LinkedList<Etape> gestionEtapes;

    public GestionnaireEtapes() {
        gestionEtapes = new LinkedList<>();
    }

    public void ajouter(Etape... etapes) {
        this.gestionEtapes.addAll(Arrays.asList(etapes));
    }

    public int nbSEtapes(){
        return gestionEtapes.size();
    }


    @Override
    public Iterator<Etape> iterator() {
        return null;
    }
}
