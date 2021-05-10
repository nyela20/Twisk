package twisk.exceptionstwiskIG;

import javafx.scene.control.Alert;

public abstract class TwiskException extends Exception {

    /**
     * Constructeur de la classe mère qui afficher le message de l'exception
     * à l'intérieur d'une Boite De Dialogue (en l'occurence ici Alert)
     *
     * @param message le message de l'exception
     */
    public TwiskException(String message, String type) {
        super(message);
        if (type.equals("IG")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeight(500);
            alert.setWidth(500);
            alert.setContentText(message);
            alert.showAndWait();
        }
    }
}

