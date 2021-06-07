package twisk.ecouteurs;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import twisk.mondeIG.MondeIG;

public class EcouteurSimulation extends EcouteurAbstractMenu {
    private final Button boutonSimuler;

    public EcouteurSimulation(MondeIG monde, Button boutonSimuler) {
        super(monde);
        this.boutonSimuler = boutonSimuler;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (getMonde().estModeCreation()) {
            try {
                getMonde().simuler();
            } catch (Exception e) { }
        }else{
            getMonde().stopSimulation();
        }
    }
}
