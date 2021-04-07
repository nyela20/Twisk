package twisk.simulation;

import twisk.monde.Etape;

public class Client {
    private int numeroClient;
    private int rangClient;
    private Etape etapeClient;

    public Client(int numero){
        numeroClient = numero;
    }

    public void allerA(Etape etape,int rang){
        etapeClient = etape;
        rangClient = rang;
    }

    public int getNumeroClient(){
        return numeroClient;
    }

    public int getRangClient(){
        return rangClient;
    }

    public Etape getEtapeClient(){
        return etapeClient;
    }
}
