package twisk.ecouteurs;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import twisk.mondeIG.MondeIG;

public class EcouteurMenuFichierQuitter extends EcouteurAbstractMenu {

    /**
     * Ecouteur qui sert à Quitter l'interface graphique
     */
    public EcouteurMenuFichierQuitter(){
        super(new MondeIG());
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Platform.exit();
    }
}
