package twisk.ecouteurs;

import javafx.event.ActionEvent;
import twisk.exceptionstwiskIG.ExceptionVueMenu;
import twisk.mondeIG.MondeIG;


public class EcouteurMenuEcartTemps extends EcouteurAbstractMenu {

    /**
     *  Ecouteur qui sert à assigner/modifier un EcartTemps
     *  d'une EtapeIG dans le Monde
     * @param mde le monde
     */
    public EcouteurMenuEcartTemps(MondeIG mde) {
        super(mde);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try{
            if(super.getMonde().nombreActiviteSelectionne() > 1){
                throw new ExceptionVueMenu("Il ya trop d'Etapes séléctionnés, il faut en séléctionner un seul pour cet action.");
            }
            super.getBoiteDialogue().setHeaderText("Assigner un écart-temps");
            super.getBoiteDialogue().showAndWait();
            String ecarttemps = super.getBoiteDialogue().getEditor().getText();
            if(!(estUnEntier(ecarttemps)) || Integer.parseInt(ecarttemps) <= 0){
                throw new ExceptionVueMenu("Valeur invalide.");
            }else {
                super.getMonde().assignerEcartTempsAEtape(Integer.parseInt(ecarttemps));
            }
        }catch(ExceptionVueMenu e){ }
    }
}
