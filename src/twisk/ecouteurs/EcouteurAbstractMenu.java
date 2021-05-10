package twisk.ecouteurs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import twisk.mondeIG.MondeIG;

public abstract class EcouteurAbstractMenu implements EventHandler<ActionEvent> {
    private final MondeIG monde;
    private final TextInputDialog boiteDialogue = new TextInputDialog();


    public EcouteurAbstractMenu(MondeIG monde){
        this.monde = monde;
    }


    @Override
    public abstract void handle(ActionEvent actionEvent);

    /**
     * La fonction retourne vrai si le String est composé uniquement d'entier
     * de [0 à 9]*, sinon faux
     *
     * @param chaine la chaine à verifier
     * @return vrai si c'est un entier, sinon faux
     */
    public boolean estUnEntier(String chaine) {
        try {
            Integer.parseInt(chaine);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    /**
     * retourne l'instance du monde
     * @return le monde
     */
    public MondeIG getMonde() {
        return monde;
    }

    /**
     * retourne une instance de boîte de dialogue
     * @return une boîte de dialogue
     */
    public TextInputDialog getBoiteDialogue() {
        return boiteDialogue;
    }
}
