package twisk.ecouteurs;

import javafx.event.ActionEvent;
import javafx.scene.control.TextInputDialog;
import twisk.exceptionstwisk.ExceptionVueMenu;
import twisk.monde.MondeIG;

public class EcouteurMenuDelai extends EcouteurAbstractMenu {


    /**
     * Ecouteur qui sert à assigner/modifier le délai
     * d'une Etape dans le Monde
     * @param mde le monde
     */
    public EcouteurMenuDelai(MondeIG mde){
        super(mde);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try{
            if(super.getMonde().nombreActiviteSelectionne() >1){
                throw new ExceptionVueMenu("Il ya trop d'Etapes séléctionnés, il faut en séléctionner un seul pour cet action.");
            }
            super.getBoiteDialogue().setHeaderText("Assigner un délai");
            super.getBoiteDialogue().showAndWait();
            String delai = super.getBoiteDialogue().getEditor().getText();
            if(!estUnEntier(delai)){
                throw new ExceptionVueMenu("Type de valeur invalide. entier uniquement");
            }else {
                super.getMonde().assignerDelaiAEtape(Integer.parseInt(delai));
            }
        }catch(ExceptionVueMenu e){
            System.out.println(e.getMessage());
        }
    }
}
