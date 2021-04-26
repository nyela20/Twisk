package twisk.monde;

import twisk.exceptionstwisk.ExceptionObjetNonTrouve;

import java.util.HashMap;
import java.util.Iterator;

public class CorrespondanceEtapes{
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
