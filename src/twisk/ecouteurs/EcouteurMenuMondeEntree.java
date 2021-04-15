package twisk.ecouteurs;

import javafx.event.ActionEvent;
import twisk.mondeIG.MondeIG;

public class EcouteurMenuMondeEntree extends EcouteurAbstractMenu {

    /**
     * Ecouteur qui sert à assigner une/plusieurs Etape en entrée dans le Monde
     * @param mde le monde
     */
    public EcouteurMenuMondeEntree(MondeIG mde){
        super(mde);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
       super.getMonde().assignerEntree();
    }
}
