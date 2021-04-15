package twisk.ecouteurs;

import javafx.event.ActionEvent;
import twisk.mondeIG.MondeIG;

public class EcouteurMenuSupprimerEtapeArc extends EcouteurAbstractMenu {

    /**
     * Ecouteur qui sert à supprimer les Arcs
     * et les Etapes qui sont séléctionnés dans le monde
     * @param mde le monde
     */
    public EcouteurMenuSupprimerEtapeArc(MondeIG mde){
        super(mde);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        super.getMonde().supprimerEtapesEtArcsSelectionnes();
    }
}
