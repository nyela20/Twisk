package twisk.ecouteurs;

import javafx.event.ActionEvent;
import twisk.mondeIG.MondeIG;

import javax.swing.*;

public class EcouteurMenuFichierQuitter extends EcouteurAbstractMenu {
    /**
     * Ecouteur qui sert à Quitter l'interface graphique
     */
    public EcouteurMenuFichierQuitter(MondeIG mde){
        super(mde);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
                System.exit(0);
    }
}
