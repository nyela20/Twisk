package twisk.ecouteurs;

import javafx.event.ActionEvent;
import twisk.exceptionstwiskIG.ExceptionMondeIG;
import twisk.exceptionstwiskIG.ExceptionVueMenu;
import twisk.mondeIG.MondeIG;

public class EcouteurOutilsAssignerNombreDeClients extends EcouteurAbstractMenu {

    public EcouteurOutilsAssignerNombreDeClients(MondeIG mondeIG) throws ExceptionMondeIG {
        super(mondeIG);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            super.getBoiteDialogue().setHeaderText("Assigner nombre de Clients");
            super.getBoiteDialogue().showAndWait();
            String nbClients = super.getBoiteDialogue().getEditor().getText();
            if (!(estUnEntier(nbClients)) || Integer.parseInt(nbClients) <= 0) {
                throw new ExceptionVueMenu("Valeur invalide.");
            }
            if(Integer.parseInt(nbClients) >=50) {
                throw new ExceptionVueMenu("Valeur invalide car >= à 50 clients");
            }
            else {
                super.getMonde().setNombreDeClients(Integer.parseInt(nbClients));
            }
        } catch (ExceptionVueMenu e) {
        }
    }
}
