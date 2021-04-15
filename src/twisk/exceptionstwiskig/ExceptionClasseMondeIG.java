package twisk.exceptionstwiskig;

public class ExceptionClasseMondeIG extends TwiskException{

    /**
     * Constructeur ExceptionClasseMondeIG qui est appellé pour toutes les
     * exceptions qui peuvent être levés à partir de la classe MondeIG
     * @param message le message de l'exception
     */
    public ExceptionClasseMondeIG(String message) {
        super(message);
    }
}
