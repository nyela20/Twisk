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
        try {
            int reponse = JOptionPane.showConfirmDialog(null, "Vous êtes sur le point de quitter Twisk ?", "Vraiment ?", JOptionPane.YES_NO_OPTION);
            if (reponse == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
