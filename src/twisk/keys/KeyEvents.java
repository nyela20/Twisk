package twisk.keys;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import twisk.ecouteurs.*;
import twisk.mondeIG.MondeIG;

public class KeyEvents implements EventHandler<KeyEvent> {
    private final MondeIG mondeIG;

    public KeyEvents(MondeIG monde) {
        mondeIG = monde;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.isControlDown()) {
            if (keyEvent.getCode() == KeyCode.R) {
                new EcouteurMenuRenommer(mondeIG).handle(new ActionEvent());
            }
            if (keyEvent.getCode() == KeyCode.F) {
                new EcouteurMenuEffacerLaSelection(mondeIG).handle(new ActionEvent());
            }
            if (keyEvent.getCode() == KeyCode.D){
                new EcouteurMenuDelai(mondeIG).handle(new ActionEvent());
            }
            if (keyEvent.getCode() == KeyCode.T){
                new EcouteurMenuEcartTemps(mondeIG).handle(new ActionEvent());
            }
            if (keyEvent.getCode() == KeyCode.J){
                new EcouteurMenuNombreDeJetons(mondeIG).handle(new ActionEvent());
            }
            if (keyEvent.getCode() == KeyCode.A){
                mondeIG.ajouter("Activite");
            }
            if (keyEvent.getCode() == KeyCode.G){
                mondeIG.ajouter("Guichet");
            }
            if(keyEvent.isShiftDown()){
                if(keyEvent.getCode() == KeyCode.E){
                    new EcouteurMenuMondeEntree(mondeIG).handle(new ActionEvent());
                }
                if(keyEvent.getCode() == KeyCode.S){
                    new EcouteurMenuMondeSortie(mondeIG).handle(new ActionEvent());
                }
                if(keyEvent.getCode() == KeyCode.ESCAPE){
                    new EcouteurMenuFichierQuitter().handle(new ActionEvent());
                }
            }
        }
        if(keyEvent.getCode() == KeyCode.DELETE){
            new EcouteurMenuSupprimerEtapeArc(mondeIG).handle(new ActionEvent());
        }

    }
}
