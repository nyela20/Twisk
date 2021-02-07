package twisk.monde;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class GestionnaireSuccesseurs implements Iterable<Etape> {

    private final LinkedList<Etape> listEtaps ;

    public GestionnaireSuccesseurs(){
        listEtaps = new LinkedList<>();
    }

    public void ajouter(Etape...etapes){
        listEtaps.addAll(Arrays.asList(etapes));
    }

    public int nbSEtapes(){
        return listEtaps.size();
    }

    @Override
    public Iterator<Etape> iterator() {
        return null;
    }

    @Override
    public String toString() {
        return "";
    }
}
