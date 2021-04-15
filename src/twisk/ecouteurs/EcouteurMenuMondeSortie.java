package twisk.ecouteurs;

import javafx.event.ActionEvent;
import twisk.mondeIG.MondeIG;

public class EcouteurMenuMondeSortie extends EcouteurAbstractMenu {

    /**
     * Ecouteur qui sert à assigner une/plusieurs Etape en sortie dans le Monde
     * @param mde le monde
     */
    public EcouteurMenuMondeSortie(MondeIG mde){
        super(mde);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        super.getMonde().assignerSortie();
    }
}
