package twisk.mondeIG;

import org.junit.jupiter.api.condition.OS;
import twisk.simulation.Client;
import twisk.vues.Observateur;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class SujetObserve{
    private final ArrayList<Observateur> ObservateurVue = new ArrayList<>();

    public SujetObserve(){ }
    /**
     * ajoute un Observateur au monde
     * @param obs l'observateur
     */
    public void ajouter(Observateur obs) {
        ObservateurVue.add(obs);
        obs.reagir();
    }

    /**
     * notifie les Observateurs du monde
     */
    public void notifierObservateur() {
        for (Observateur vue : ObservateurVue) {
            vue.reagir();
        }
    }
}
