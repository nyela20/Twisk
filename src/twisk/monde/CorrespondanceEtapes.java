package twisk.monde;

import twisk.mondeIG.EtapeIG;

import java.io.Serializable;
import java.util.HashMap;

public class CorrespondanceEtapes implements Serializable {
    private final HashMap<EtapeIG,Etape> tableauEtapes = new HashMap<>();

    public CorrespondanceEtapes(){ }

    public void ajouter(EtapeIG etapeIG,Etape etape){
        tableauEtapes.put(etapeIG,etape);
    }

    public Etape get(EtapeIG etapeIG){
        for(Etape etape : tableauEtapes.values()){
            if(etapeIG.getNom().equals(etape.getNom())){
                return etape;
            }
        }
        return null;
    }
}
