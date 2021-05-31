package twisk.ecouteurs;

import javafx.event.ActionEvent;
import twisk.mondeIG.MondeIG;

import java.text.Normalizer;

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
        String nouveauNom = sansAccents(super.getBoiteDialogue().getEditor().getText());
        super.getMonde().renommerEtape(nouveauNom);
    }

    public static String sansAccents(String source) {
        return Normalizer.normalize(source, Normalizer.Form.NFD).replaceAll("[\u0300-\u036F -+.^:,@#{|}&'(_)=]", "");
    }
}
