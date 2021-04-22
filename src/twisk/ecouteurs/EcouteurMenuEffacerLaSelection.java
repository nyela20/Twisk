package twisk.ecouteurs;

import javafx.event.ActionEvent;
import twisk.monde.MondeIG;

public class EcouteurMenuEffacerLaSelection extends EcouteurAbstractMenu {

    /**
     * Ecouteur qui sert à effacer les Etapes/Arcs selectionnés
     * dans le Monde
     * @param mde le monde
     */
    public EcouteurMenuEffacerLaSelection(MondeIG mde){
        super(mde);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        super.getMonde().effacerLaSelection();
    }
}
