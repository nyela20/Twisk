package twisk.exceptionstwiskIG;

import javafx.scene.control.Alert;

public abstract class TwiskException extends Exception {

    /**
     * Constructeur de la classe mère qui afficher le message de l'exception
     * à l'intérieur d'une Boite De Dialogue (en l'occurence ici Alert)
     *
     * @param message le message de l'exception
     */
    public TwiskException(String message) {
        super(message);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setResizable(true);
        alert.getDialogPane().setMinHeight(200);
        alert.getDialogPane().setMinWidth(750);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

