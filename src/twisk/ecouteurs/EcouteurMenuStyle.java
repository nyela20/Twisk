package twisk.ecouteurs;

import javafx.event.ActionEvent;
import twisk.monde.MondeIG;

public class EcouteurMenuStyle extends EcouteurAbstractMenu {
    private final int style;

    /**
     * Constructeur dans le monde qui sert à set un identifiant d'un style à un monde
     * par exemple le style1  identifiant 1
     * il y a autant d'identifiant que de style possible
     * (voir css)
     * @param mde monde
     * @param style pour style
     */
    public EcouteurMenuStyle(MondeIG mde,int style){
        super(mde);
        this.style = style;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        super.getMonde().setIdentifiantStyle(style);
    }
}
