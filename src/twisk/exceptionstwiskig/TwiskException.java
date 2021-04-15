package twisk.exceptionstwiskig;

import javafx.scene.control.Alert;

public abstract class TwiskException extends Exception{

    /**
     * Constructeur de la classe mère qui afficher le message de l'exception
     * à l'intérieur d'une Boite De Dialogue (en l'occurence ici Alert)
     * @param message le message de l'exception
     */
    public TwiskException(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
