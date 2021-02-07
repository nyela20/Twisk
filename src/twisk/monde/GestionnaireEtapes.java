package twisk.monde;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class GestionnaireEtapes implements Iterable<Etape> {

    private final LinkedList<Etape> gestionEtapes;

    public GestionnaireEtapes() {
        gestionEtapes = new LinkedList<>();
    }

    public void ajouter(Etape... etapes) {
        this.gestionEtapes.addAll(Arrays.asList(etapes));
    }

    public int nbEtapes(){
        return gestionEtapes.size();
    }

    public int nbGuichet(){
        int compt = 0;
        for (Etape e : this.gestionEtapes){
            if(e.estUnGuichet()){
                compt++;
            }
        }
        return compt;
    }


    @Override
    public Iterator<Etape> iterator() {
        return gestionEtapes.iterator();
    }
}
