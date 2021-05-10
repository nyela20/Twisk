package twisk.exceptionstwiskIG;

public class ExceptionsInvaliditeSurLesArcs extends TwiskException{

    /**
     * Constructeur ExceptionsInvaliditeSurLesArcs qui est appellé pour toutes les
     * exceptions qui peuvent être lévés, concernent les ArcIGs
     * @param message le message de l'exception
     */
    public ExceptionsInvaliditeSurLesArcs(String message){
        super(message,"IG");
    }
}
