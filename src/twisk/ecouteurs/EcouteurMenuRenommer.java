package twisk.ecouteurs;

import javafx.event.ActionEvent;
import javafx.scene.control.TextInputDialog;
import twisk.monde.MondeIG;

public class EcouteurMenuRenommer extends EcouteurAbstractMenu {

    /**
     * Constructeur le l'ecouteur qui sert à renomme une Etape dans le monde
     * @param mde le monde
     */
    public EcouteurMenuRenommer(MondeIG mde){
        super(mde);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        super.getBoiteDialogue().setHeaderText("renommer");
        super.getBoiteDialogue().showAndWait();
        super.getMonde().renommerEtape(super.getBoiteDialogue().getEditor().getText());
    }
}
