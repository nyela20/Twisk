package twisk.monde;

import java.util.Iterator;

public class Monde implements Iterable<Etape> {

    private Etape SasSortie;
    private Etape SasEntree;
    //private GestionnaireEtapes GestionnaireDesEtapes;


    public Monde(){
        SasEntree sasEntree = new SasEntree();
        SasSortie sasSortie = new SasSortie();
    }

    void aCommeEntree(Etape ... etapes){
    }


    @Override
    public Iterator<Etape> iterator() {
        return null;
    }
}
