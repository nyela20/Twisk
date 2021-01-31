package twisk.monde;


import java.util.Iterator;

public abstract class Etape  {

    private final String nom;

    Etape (String nom){
        this.nom = nom;
    }

    void ajouterSuccesseur(Etape ... e){

    }

    boolean estUneActivite(){
        return false;
    }

    boolean estUnGuichet(){
        return false;
    }

    Iterator<Etape> iterator(){
        return null;
    }
}
