package twisk.ecouteurs;

import javafx.event.ActionEvent;
import twisk.exceptionstwiskIG.ExceptionVueMenu;
import twisk.mondeIG.MondeIG;

public class EcouteurMenuNombreDeJetons extends EcouteurAbstractMenu {

    public EcouteurMenuNombreDeJetons(MondeIG mde){
        super(mde);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            if (super.getMonde().nombreActiviteSelectionne() > 1) {
                throw new ExceptionVueMenu("Il ya trop d'Etapes séléctionnés, il faut en séléctionner un seul pour cet action.");
            }
            super.getBoiteDialogue().setHeaderText("Combien de jetons ?");
            super.getBoiteDialogue().showAndWait();
            String nombreDeJetons = super.getBoiteDialogue().getEditor().getText();
            if (estUnEntier(nombreDeJetons)  || Integer.parseInt(nombreDeJetons) <= 0) {
                throw new ExceptionVueMenu("Valeur invalide.");
            } else {
                super.getMonde().assignerNombreDeJetonsAEtape(Integer.parseInt(nombreDeJetons));
            }
        } catch (ExceptionVueMenu e) {
            System.out.println(e.getMessage());
        }
    }
}
