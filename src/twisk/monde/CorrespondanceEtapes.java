package twisk.monde;

import twisk.exceptionstwisk.ExceptionObjetNonTrouve;

import java.util.HashMap;
import java.util.Iterator;

public class CorrespondanceEtapes implements Iterable<Etape> {
    private final HashMap<EtapeIG,Etape> tableauEtapes = new HashMap<>();

    public CorrespondanceEtapes(){ }

    public void ajouter(EtapeIG etapeIG, Etape etape){
        tableauEtapes.put(etapeIG,etape);
        System.out.println("monde.ajouter(" + etape.getNom() + ");");
    }

    /**
     * Cette fonction est appelée lors de la définition des successeurs des étapes
     * du monde à partir des successeurs des étapes graphiques
     * @param etapeIG l'EtapeIg
     * @return la correspondance etape de etapeig
     * @throws ExceptionObjetNonTrouve lève exceptions si non trouvé
     */
    public Etape get(EtapeIG etapeIG) throws ExceptionObjetNonTrouve {
        for(Etape etape: tableauEtapes.values()){
            if(etape.getNom().equals(etapeIG.getNom())){
                return etape;
            }
        }
        throw new ExceptionObjetNonTrouve("Aucune étape correspondant trouvé");
    }

    @Override
    public Iterator<Etape> iterator() {
        return tableauEtapes.values().iterator();
    }

    public EtapeIG getKey(Etape etape) throws ExceptionObjetNonTrouve {
        for(EtapeIG etapeIG: tableauEtapes.keySet()){
            if(etape.getNom().equals(etapeIG.getNom())){
                return etapeIG;
            }
        }
        throw new ExceptionObjetNonTrouve("Aucune étape correspondant trouvé");
    }
}
