package twisk.exceptionstwisk;

public class ExceptionMondeIG extends TwiskException{

    /**
     * Constructeur ExceptionMondeIG qui est appellé pour toutes les
     * exceptions qui peuvent être levés à partir de la classe MondeIG
     * @param message le message de l'exception
     */
    public ExceptionMondeIG(String message) {
        super(message);
    }
}
