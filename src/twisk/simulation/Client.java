package twisk.simulation;

import twisk.monde.Etape;

import java.io.Serializable;

public class Client implements Serializable {
    private final int numeroClient;
    private int rang;
    private Etape etapeClient;

    public Client(int numero){
        numeroClient = numero;
    }

    public void allerA(Etape etape,int rang){
        etapeClient = etape;
        this.rang = rang;
    }

    public boolean estDans(String nomEtape){
        return etapeClient.getNom().equals(nomEtape);
    }

    public int getNumeroClient(){
        return numeroClient;
    }

    public int getRang(){
        return rang;
    }

    public Etape getEtapeClient(){
        return etapeClient;
    }
}
