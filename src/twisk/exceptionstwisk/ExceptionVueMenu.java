package twisk.exceptionstwisk;

public class ExceptionVueMenu extends TwiskException {

    /**
     * Constructeur ExceptionsInvaliditeSurLesArcs qui est appellé pour toutes les
     * exceptions qui peuvent être lévés, à partri des évenements du Menu
     * @param message le message de l'exception
     */
    public ExceptionVueMenu(String message) {
        super(message);
    }
}
