package twisk.monde;

import twisk.vues.Observateur;

import java.util.ArrayList;

public abstract class SujetObserve {
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
