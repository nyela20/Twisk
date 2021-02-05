package twisk.monde;

public class Monde /*implements Iterable*/ {

    private Etape SasSortie;
    private Etape SasEntree;
    //private GestionnaireEtapes GestionnaireDesEtapes;

    public Monde(){
        Etape SasEntree = new Activite("SasEntree");
        Etape SasSortie = new Activite("SasSortie");
    }

    void aCommeEntree(Etape ... etapes){

    }



}
